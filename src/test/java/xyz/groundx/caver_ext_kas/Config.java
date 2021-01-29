/*
 * Copyright 2020 The caver-java-ext-kas Authors
 *
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.groundx.caver_ext_kas;

import com.klaytn.caver.Caver;
import com.klaytn.caver.abi.ABI;
import com.klaytn.caver.contract.ContractDeployParams;
import com.klaytn.caver.contract.SendOptions;
import com.klaytn.caver.kct.kip17.KIP17;
import com.klaytn.caver.kct.kip17.KIP17ConstantData;
import com.klaytn.caver.kct.kip17.KIP17DeployParams;
import com.klaytn.caver.kct.kip7.KIP7;
import com.klaytn.caver.kct.kip7.KIP7ConstantData;
import com.klaytn.caver.kct.kip7.KIP7DeployParams;
import com.klaytn.caver.methods.response.Bytes32;
import com.klaytn.caver.methods.response.Quantity;
import com.klaytn.caver.methods.response.TransactionReceipt;
import com.klaytn.caver.transaction.response.PollingTransactionReceiptProcessor;
import com.klaytn.caver.transaction.response.TransactionReceiptProcessor;
import com.klaytn.caver.transaction.type.SmartContractDeploy;
import com.klaytn.caver.transaction.type.SmartContractExecution;
import com.klaytn.caver.transaction.type.ValueTransfer;
import com.klaytn.caver.utils.CodeFormat;
import com.klaytn.caver.utils.Utils;
import com.klaytn.caver.wallet.KeyringContainer;
import com.klaytn.caver.wallet.keyring.KeyringFactory;
import com.klaytn.caver.wallet.keyring.SingleKeyring;
import com.squareup.okhttp.Credentials;
import io.github.cdimascio.dotenv.Dotenv;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class Config {
    public static final String URL_NODE_API = "https://node-api.klaytnapi.com/v1/klaytn";
    public static final String URL_ANCHOR_API = "https://anchor-api.klaytnapi.com";
    public static final String URL_TH_API = "https://th-api.klaytnapi.com";
    public static final String URL_WALLET_API = "https://wallet-api.klaytnapi.com";

    public static final String CHAIN_ID_BAOBOB = "1001";

    static String accessKey = "";
    static String secretAccessKey = "";

    public static String feePayerAddress = "";
    public static String operatorAddress = "";

    static String klayProviderPrivateKey = "";

    public static Integer presetID;



    public static CaverExtKAS caver;
    public static KeyringContainer keyringContainer;

    public static SingleKeyring klayProviderKeyring;

    public static String loadEnvData(Dotenv env, String envName) {

        String data = System.getenv(envName);

        if(data == null) {
            data = env.get(envName);
        }

        if(data.equals("")) {
            throw new NullPointerException(envName + " is not exist.");
        }

        return data;
    }

    public static void init() {
        loadTestData();

        caver = new CaverExtKAS();
        caver.initNodeAPI(CHAIN_ID_BAOBOB, accessKey, secretAccessKey, URL_NODE_API);
        caver.initAnchorAPI(CHAIN_ID_BAOBOB, accessKey, secretAccessKey, URL_ANCHOR_API);
        caver.initWalletAPI(CHAIN_ID_BAOBOB, accessKey, secretAccessKey, URL_WALLET_API);
        caver.initTokenHistoryAPI(CHAIN_ID_BAOBOB, accessKey, secretAccessKey, URL_TH_API);

        keyringContainer = new KeyringContainer();
        klayProviderKeyring = (SingleKeyring)keyringContainer.add(KeyringFactory.createFromPrivateKey(klayProviderPrivateKey));
    }

    public static void loadTestData() {
        Dotenv env = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        accessKey = accessKey.equals("") ? loadEnvData(env, "ACCESS_KEY") : accessKey;
        secretAccessKey = secretAccessKey.equals("") ? loadEnvData(env, "SECRET_ACCESS_KEY") : secretAccessKey;
        feePayerAddress = feePayerAddress.equals("") ? loadEnvData(env, "FEE_PAYER_ADDR") : feePayerAddress;
        operatorAddress = operatorAddress.equals("") ? loadEnvData(env, "OPERATOR") : operatorAddress;
        klayProviderPrivateKey = klayProviderPrivateKey.equals("") ? loadEnvData(env, "SENDER_PRV_KEY") : klayProviderPrivateKey;

        presetID = presetID == null ? Integer.parseInt(loadEnvData(env, "PRESET")) : presetID;
    }

    public static TransactionReceipt.TransactionReceiptData sendValue(String toAddress) throws IOException, TransactionException {
        init();

        BigInteger value = new BigInteger(Utils.convertToPeb("1", Utils.KlayUnit.KLAY));

        ValueTransfer valueTransfer = new ValueTransfer.Builder()
                .setKlaytnCall(caver.rpc.getKlay())
                .setFrom(klayProviderKeyring.getAddress())
                .setTo(toAddress)
                .setValue(value)
                .setGas(BigInteger.valueOf(25000))
                .build();

        keyringContainer.sign(klayProviderKeyring.getAddress(), valueTransfer);
        Bytes32 result = caver.rpc.klay.sendRawTransaction(valueTransfer.getRawTransaction()).send();
        if(result.hasError()) {
            throw new RuntimeException(result.getError().getMessage());
        }

        //Check transaction receipt.
        TransactionReceiptProcessor transactionReceiptProcessor = new PollingTransactionReceiptProcessor(caver, 1000, 15);
        TransactionReceipt.TransactionReceiptData transactionReceipt = transactionReceiptProcessor.waitForTransactionReceipt(result.getResult());

        return transactionReceipt;
    }

    public static BigInteger getBalance(String address) {
        init();
        try {
            Quantity response = caver.rpc.klay.getBalance(address).send();
            System.out.println(Utils.convertFromPeb(new BigDecimal(response.getValue()), Utils.KlayUnit.KLAY));

            return response.getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String deployKIP7(Caver caver, String deployer) {
        try {
            KIP7 kip7 = new KIP7(caver);

            BigInteger initialSupply = BigInteger.valueOf(100_000).multiply(BigInteger.TEN.pow(18)); // 100000 * 10^18
            ContractDeployParams contractDeployParams = new ContractDeployParams(KIP7ConstantData.BINARY, "TEST", "TES", 18, initialSupply);

            String input = ABI.encodeContractDeploy(kip7.getConstructor(), contractDeployParams.getBytecode(), contractDeployParams.getDeployParams());

            SmartContractDeploy deployTx = new SmartContractDeploy.Builder()
                    .setKlaytnCall(caver.rpc.klay)
                    .setFrom(deployer)
                    .setInput(input)
                    .setCodeFormat(CodeFormat.EVM)
                    .setHumanReadable(false)
                    .setGas(BigInteger.valueOf(5500000))
                    .build();

            keyringContainer.sign(deployer, deployTx);
            Bytes32 res = caver.rpc.klay.sendRawTransaction(deployTx).send();
            PollingTransactionReceiptProcessor processor = new PollingTransactionReceiptProcessor(caver, 1000, 15);
            TransactionReceipt.TransactionReceiptData receiptData = processor.waitForTransactionReceipt(res.getResult());

            return receiptData.getContractAddress();
        } catch (IOException | ReflectiveOperationException | TransactionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String deployKIP17(Caver caver, String deployer) {
        try {
            String contractName = "TEST_KIP17";
            String contractSymbol = "KIP17";

            KIP17 kip17 = new KIP17(caver);
            ContractDeployParams contractDeployParams = new ContractDeployParams(KIP17ConstantData.BINARY, contractName, contractSymbol);

            String input = ABI.encodeContractDeploy(kip17.getConstructor(), contractDeployParams.getBytecode(), contractDeployParams.getDeployParams());

            SmartContractDeploy deployTx = new SmartContractDeploy.Builder()
                    .setKlaytnCall(caver.rpc.klay)
                    .setFrom(deployer)
                    .setInput(input)
                    .setCodeFormat(CodeFormat.EVM)
                    .setHumanReadable(false)
                    .setGas(BigInteger.valueOf(5500000))
                    .build();

            keyringContainer.sign(deployer, deployTx);
            Bytes32 res = caver.rpc.klay.sendRawTransaction(deployTx).send();
            PollingTransactionReceiptProcessor processor = new PollingTransactionReceiptProcessor(caver, 1000, 15);
            TransactionReceipt.TransactionReceiptData receiptData = processor.waitForTransactionReceipt(res.getResult());

            return receiptData.getContractAddress();
        } catch (IOException | ReflectiveOperationException | TransactionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void mintKIP17Token(Caver caver, String contractAddress, String ownerAddress, BigInteger tokenId) throws Exception {
        KIP17 kip17 = new KIP17(caver, contractAddress);
        SendOptions sendOptions = new SendOptions(ownerAddress, BigInteger.valueOf(5500000));

        String input = kip17.getMethod("mint").encodeABI(Arrays.asList(ownerAddress, tokenId));

        SmartContractExecution smartContractExecution = new SmartContractExecution.Builder()
                .setKlaytnCall(caver.rpc.klay)
                .setFrom(ownerAddress)
                .setTo(contractAddress)
                .setInput(input)
                .setGas(BigInteger.valueOf(5500000))
                .build();

        keyringContainer.sign(ownerAddress, smartContractExecution);
        Bytes32 res = caver.rpc.klay.sendRawTransaction(smartContractExecution).send();
        PollingTransactionReceiptProcessor processor = new PollingTransactionReceiptProcessor(caver, 1000, 15);
        TransactionReceipt.TransactionReceiptData receiptData = processor.waitForTransactionReceipt(res.getResult());

    }

    public static CaverExtKAS getCaver() {
        return caver;
    }

    public static String getFeePayerAddress() {
        return feePayerAddress;
    }

    public static String getOperatorAddress() {
        return operatorAddress;
    }

    public static SingleKeyring getKlayProviderKeyring() {
        return klayProviderKeyring;
    }

    public static Integer getPresetID() {
        return presetID;
    }
}
