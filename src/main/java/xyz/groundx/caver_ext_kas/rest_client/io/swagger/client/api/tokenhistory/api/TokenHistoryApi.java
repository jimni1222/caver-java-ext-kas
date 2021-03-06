/*
 * Token History API
 * # Introduction  Token History API는 KLAY, FT (KIP-7, Labeled ERC-20), NFT (KIP-17, Labeled ERC-721) 토큰 정보, 이들 토큰을 주고받은 기록을 조회하는 기능을 제공합니다. 여러분은 특정 EOA가 KLAY를 주고받은 기록을 확인하거나 EOA가 가지고 있는 NFT 정보를 불러오는 등 Token History API를 다양하게 활용할 수 있습니다.   Token History API 사용에 관한 자세한 내용은 [튜토리얼](https://klaytn.com)을 확인하십시오.   이 문서 혹은 KAS에 관한 문의는 [개발자 포럼](https://forum.klaytn.com/)을 방문해 도움을 받으십시오  
 *
 * OpenAPI spec version: 0.7.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.api;

import com.google.gson.reflect.TypeToken;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.model.PageableTransfers;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.model.Transfers;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenHistoryApi {
    private ApiClient apiClient;

    public TokenHistoryApi() {
        this(Configuration.getDefaultApiClient());
    }

    public TokenHistoryApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for getTransfers
     * @param xChainId  Klaytn 네트워크 체인 ID (1001 or 8217) (required)
     * @param presets  (csv) 검색에 사용할 Preset ID들, Preset ID는 KAS Console에서 확인 (required)
     * @param kind (csv) [“klay”, “ft”, “nft”] 중 포함 할 유형, 지정안될 경우 모든 유형을 조회  (optional)
     * @param range 조회 범위 지정 (블록번호 또는 Unix time) (optional)
     * @param size 응답 아이템 개수 (min&#x3D;1, max&#x3D;1000, default&#x3D;100) (optional)
     * @param cursor 응답 오프셋 (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getTransfersCall(String xChainId, String presets, String kind, String range, Long size, String cursor, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/v2/transfer";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (kind != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("kind", kind));
        if (range != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("range", range));
        if (size != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("size", size));
        if (cursor != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("cursor", cursor));
        if (presets != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("presets", presets));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xChainId != null)
        localVarHeaderParams.put("x-chain-id", apiClient.parameterToString(xChainId));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "basic" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getTransfersValidateBeforeCall(String xChainId, String presets, String kind, String range, Long size, String cursor, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'xChainId' is set
        if (xChainId == null) {
            throw new ApiException("Missing the required parameter 'xChainId' when calling getTransfers(Async)");
        }
        // verify the required parameter 'presets' is set
        if (presets == null) {
            throw new ApiException("Missing the required parameter 'presets' when calling getTransfers(Async)");
        }
        
        com.squareup.okhttp.Call call = getTransfersCall(xChainId, presets, kind, range, size, cursor, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * 토큰 송/수신 기록 조회
     * 거래내역을 통합 조회합니다. 거래내역은 KLAY 전송 (&#x60;KlayTransfer&#x60;), FT 전송 (&#x60;FtTransfer&#x60;), NFT 전송 (&#x60;NftTransfer&#x60;)으로 나뉩니다.   ## FT/NFT 거래내역 조회 결과에 포함된 KlayTransfer   FT, NFT 전송의 경우 토큰 전송을 실행한 &#x60;KlayTransfer&#x60; 전송내역이 조회 결과에 포함됩니다. 이것은 FT/NFT 토큰 전송은 기본적으로 FT/NFT 컨트랙트 함수의 실행이기 때문이며 &#x60;transfer&#x60;는 토큰 전송 함수를 실행하는 트랜잭션에 대응하는 &#x60;KlayTransfer&#x60;를 응답에 포함합니다. 컨트랙트 함수를 실행하는 트랜잭션들은 대체로 KLAY를 전송하지 않기 때문에 FT, NFT 전송을 실행한 &#x60;KlayTransfer&#x60;의 &#x60;value&#x60;는 0일 수 있습니다.    ## 거래유형  * &#x60;kind&#x60; 파라메터를 설정하여 KLAY, FT, NFT 중 한 가지 유형을 골라 거래내역을 조회할 수 있습니다 * &#x60;kind&#x60;가 설정되지 않으면 모든 유형의 거래내역이 조회됩니다  ## 조회기간  * &#x60;range&#x60;의 경우 &#x60;range&#x3D;{from},{to}&#x60; 포맷으로 질의합니다 * &#x60;{from}&#x60;과 &#x60;{to}&#x60;가 정수일 경우 Unix time으로, 16진수 문자열일 경우 블록 번호로 간주합니다 * &#x60;{to}&#x60;값이 없는 경우 현재 시간 또는 최신 블록 번호가 사용됩니다 * 한 번에 불러올 수 있는 거래내역은 최대 과거 6개월 분량입니다(Unix time, 블록 두 경우 모두 해당).  ## Preset  * &#x60;presets&#x60; 쿼리 파라미터는 필수 파라미터입니다 * [Preset](https://www.klaytn.com)은 KAS Console에서 사전에 생성되어 있어야 합니다 * Preset ID는 KAS Console에서 확인하실 수 있습니다 
     * @param xChainId  Klaytn 네트워크 체인 ID (1001 or 8217) (required)
     * @param presets  (csv) 검색에 사용할 Preset ID들, Preset ID는 KAS Console에서 확인 (required)
     * @param kind (csv) [“klay”, “ft”, “nft”] 중 포함 할 유형, 지정안될 경우 모든 유형을 조회  (optional)
     * @param range 조회 범위 지정 (블록번호 또는 Unix time) (optional)
     * @param size 응답 아이템 개수 (min&#x3D;1, max&#x3D;1000, default&#x3D;100) (optional)
     * @param cursor 응답 오프셋 (optional)
     * @return PageableTransfers
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PageableTransfers getTransfers(String xChainId, String presets, String kind, String range, Long size, String cursor) throws ApiException {
        ApiResponse<PageableTransfers> resp = getTransfersWithHttpInfo(xChainId, presets, kind, range, size, cursor);
        return resp.getData();
    }

    /**
     * 토큰 송/수신 기록 조회
     * 거래내역을 통합 조회합니다. 거래내역은 KLAY 전송 (&#x60;KlayTransfer&#x60;), FT 전송 (&#x60;FtTransfer&#x60;), NFT 전송 (&#x60;NftTransfer&#x60;)으로 나뉩니다.   ## FT/NFT 거래내역 조회 결과에 포함된 KlayTransfer   FT, NFT 전송의 경우 토큰 전송을 실행한 &#x60;KlayTransfer&#x60; 전송내역이 조회 결과에 포함됩니다. 이것은 FT/NFT 토큰 전송은 기본적으로 FT/NFT 컨트랙트 함수의 실행이기 때문이며 &#x60;transfer&#x60;는 토큰 전송 함수를 실행하는 트랜잭션에 대응하는 &#x60;KlayTransfer&#x60;를 응답에 포함합니다. 컨트랙트 함수를 실행하는 트랜잭션들은 대체로 KLAY를 전송하지 않기 때문에 FT, NFT 전송을 실행한 &#x60;KlayTransfer&#x60;의 &#x60;value&#x60;는 0일 수 있습니다.    ## 거래유형  * &#x60;kind&#x60; 파라메터를 설정하여 KLAY, FT, NFT 중 한 가지 유형을 골라 거래내역을 조회할 수 있습니다 * &#x60;kind&#x60;가 설정되지 않으면 모든 유형의 거래내역이 조회됩니다  ## 조회기간  * &#x60;range&#x60;의 경우 &#x60;range&#x3D;{from},{to}&#x60; 포맷으로 질의합니다 * &#x60;{from}&#x60;과 &#x60;{to}&#x60;가 정수일 경우 Unix time으로, 16진수 문자열일 경우 블록 번호로 간주합니다 * &#x60;{to}&#x60;값이 없는 경우 현재 시간 또는 최신 블록 번호가 사용됩니다 * 한 번에 불러올 수 있는 거래내역은 최대 과거 6개월 분량입니다(Unix time, 블록 두 경우 모두 해당).  ## Preset  * &#x60;presets&#x60; 쿼리 파라미터는 필수 파라미터입니다 * [Preset](https://www.klaytn.com)은 KAS Console에서 사전에 생성되어 있어야 합니다 * Preset ID는 KAS Console에서 확인하실 수 있습니다 
     * @param xChainId  Klaytn 네트워크 체인 ID (1001 or 8217) (required)
     * @param presets  (csv) 검색에 사용할 Preset ID들, Preset ID는 KAS Console에서 확인 (required)
     * @param kind (csv) [“klay”, “ft”, “nft”] 중 포함 할 유형, 지정안될 경우 모든 유형을 조회  (optional)
     * @param range 조회 범위 지정 (블록번호 또는 Unix time) (optional)
     * @param size 응답 아이템 개수 (min&#x3D;1, max&#x3D;1000, default&#x3D;100) (optional)
     * @param cursor 응답 오프셋 (optional)
     * @return ApiResponse&lt;PageableTransfers&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<PageableTransfers> getTransfersWithHttpInfo(String xChainId, String presets, String kind, String range, Long size, String cursor) throws ApiException {
        com.squareup.okhttp.Call call = getTransfersValidateBeforeCall(xChainId, presets, kind, range, size, cursor, null, null);
        Type localVarReturnType = new TypeToken<PageableTransfers>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * 토큰 송/수신 기록 조회 (asynchronously)
     * 거래내역을 통합 조회합니다. 거래내역은 KLAY 전송 (&#x60;KlayTransfer&#x60;), FT 전송 (&#x60;FtTransfer&#x60;), NFT 전송 (&#x60;NftTransfer&#x60;)으로 나뉩니다.   ## FT/NFT 거래내역 조회 결과에 포함된 KlayTransfer   FT, NFT 전송의 경우 토큰 전송을 실행한 &#x60;KlayTransfer&#x60; 전송내역이 조회 결과에 포함됩니다. 이것은 FT/NFT 토큰 전송은 기본적으로 FT/NFT 컨트랙트 함수의 실행이기 때문이며 &#x60;transfer&#x60;는 토큰 전송 함수를 실행하는 트랜잭션에 대응하는 &#x60;KlayTransfer&#x60;를 응답에 포함합니다. 컨트랙트 함수를 실행하는 트랜잭션들은 대체로 KLAY를 전송하지 않기 때문에 FT, NFT 전송을 실행한 &#x60;KlayTransfer&#x60;의 &#x60;value&#x60;는 0일 수 있습니다.    ## 거래유형  * &#x60;kind&#x60; 파라메터를 설정하여 KLAY, FT, NFT 중 한 가지 유형을 골라 거래내역을 조회할 수 있습니다 * &#x60;kind&#x60;가 설정되지 않으면 모든 유형의 거래내역이 조회됩니다  ## 조회기간  * &#x60;range&#x60;의 경우 &#x60;range&#x3D;{from},{to}&#x60; 포맷으로 질의합니다 * &#x60;{from}&#x60;과 &#x60;{to}&#x60;가 정수일 경우 Unix time으로, 16진수 문자열일 경우 블록 번호로 간주합니다 * &#x60;{to}&#x60;값이 없는 경우 현재 시간 또는 최신 블록 번호가 사용됩니다 * 한 번에 불러올 수 있는 거래내역은 최대 과거 6개월 분량입니다(Unix time, 블록 두 경우 모두 해당).  ## Preset  * &#x60;presets&#x60; 쿼리 파라미터는 필수 파라미터입니다 * [Preset](https://www.klaytn.com)은 KAS Console에서 사전에 생성되어 있어야 합니다 * Preset ID는 KAS Console에서 확인하실 수 있습니다 
     * @param xChainId  Klaytn 네트워크 체인 ID (1001 or 8217) (required)
     * @param presets  (csv) 검색에 사용할 Preset ID들, Preset ID는 KAS Console에서 확인 (required)
     * @param kind (csv) [“klay”, “ft”, “nft”] 중 포함 할 유형, 지정안될 경우 모든 유형을 조회  (optional)
     * @param range 조회 범위 지정 (블록번호 또는 Unix time) (optional)
     * @param size 응답 아이템 개수 (min&#x3D;1, max&#x3D;1000, default&#x3D;100) (optional)
     * @param cursor 응답 오프셋 (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getTransfersAsync(String xChainId, String presets, String kind, String range, Long size, String cursor, final ApiCallback<PageableTransfers> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getTransfersValidateBeforeCall(xChainId, presets, kind, range, size, cursor, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<PageableTransfers>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getTransfersByEoa
     * @param xChainId  Klaytn 체인 네트워크 ID (1001 or 8217) (required)
     * @param address 검색기준이 되는 EOA, 검색결과의 from 또는 to가 제시된 address값과 일치 (required)
     * @param kind (csv) [“klay”, “ft”, “nft”] 중 포함 할 유형, 지정안될 경우 모든 유형을 조회  (optional)
     * @param caFilter 조회할 FT 또는 NFT 컨트랙트의 주소, 설정될 경우 &#x60;transferType&#x60;이 \&quot;ft\&quot; 또는 \&quot;nft\&quot;인 것들 중 ca-filter 값과 같은 것들만 결과물로 반환.  (optional)
     * @param range (csv) 조회 범위 지정 (블록번호 또는 Unix time) (optional)
     * @param size 응답 아이템 개수(min&#x3D;1, max&#x3D;1000, default&#x3D;100) (optional)
     * @param cursor 특정 위치를 지정하기 위한 오프셋 (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getTransfersByEoaCall(String xChainId, String address, String kind, String caFilter, String range, Long size, String cursor, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/v2/transfer/account/{address}"
            .replaceAll("\\{" + "address" + "\\}", apiClient.escapeString(address.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (kind != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("kind", kind));
        if (caFilter != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("ca-filter", caFilter));
        if (range != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("range", range));
        if (size != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("size", size));
        if (cursor != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("cursor", cursor));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xChainId != null)
        localVarHeaderParams.put("x-chain-id", apiClient.parameterToString(xChainId));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "basic" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getTransfersByEoaValidateBeforeCall(String xChainId, String address, String kind, String caFilter, String range, Long size, String cursor, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'xChainId' is set
        if (xChainId == null) {
            throw new ApiException("Missing the required parameter 'xChainId' when calling getTransfersByEoa(Async)");
        }
        // verify the required parameter 'address' is set
        if (address == null) {
            throw new ApiException("Missing the required parameter 'address' when calling getTransfersByEoa(Async)");
        }
        
        com.squareup.okhttp.Call call = getTransfersByEoaCall(xChainId, address, kind, caFilter, range, size, cursor, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * EOA로 토큰 송/수신 기록 조회
     * 특정 EOA의 토큰 거래내역을 조회합니다. 기능적으로 &#x60;GET /v2/transfer&#x60;와 같습니다. 단, 결과로 반환된 Transfer 객체에서 이 EOA가 토큰을 받았다면 &#x60;to&#x60;가, 토큰을 보냈다면 &#x60;from&#x60;이 EOA와 같습니다.     거래내역은 KLAY 전송 (&#x60;KlayTransfer&#x60;), FT 전송 (&#x60;FtTransfer&#x60;), NFT 전송 (&#x60;NftTransfer&#x60;)으로 나뉘며 FT, NFT 전송의 경우 토큰 전송을 실행한 &#x60;KlayTransfer&#x60; 전송내역이 조회 결과에 포함됩니다. FT, NFT 전송을 실행한 &#x60;KlayTransfer&#x60;의 &#x60;value&#x60;는 0일 수 있습니다. 이와 관련된 자세한 설명은 &#x60;GET /v2/transfer&#x60;를 참조하여 주시기 바랍니다.  ## 거래유형  * &#x60;kind&#x60; 파라메터를 설정하여 여러 유형의 거래내역을 조회할 수 있습니다 * &#x60;kind&#x60;가 설정되지 않으면 모든 유형의 거래내역이 조회됩니다  ## 조회기간  * &#x60;range&#x60;의 경우 &#x60;range&#x3D;{from},{to}&#x60; 포맷으로 질의합니다 * &#x60;{from}&#x60;과 &#x60;{to}&#x60;가 정수일 경우 Unix time으로, 16진수 문자열일 경우 블록 번호로 간주합니다 * &#x60;{to}&#x60;값이 없는 경우 현재 시간 또는 최신 블록 번호가 사용됩니다 * 최대 검색기간은 6개월로 제한됩니다 (Unix time, 블록 두 경우 모두 해당) 
     * @param xChainId  Klaytn 체인 네트워크 ID (1001 or 8217) (required)
     * @param address 검색기준이 되는 EOA, 검색결과의 from 또는 to가 제시된 address값과 일치 (required)
     * @param kind (csv) [“klay”, “ft”, “nft”] 중 포함 할 유형, 지정안될 경우 모든 유형을 조회  (optional)
     * @param caFilter 조회할 FT 또는 NFT 컨트랙트의 주소, 설정될 경우 &#x60;transferType&#x60;이 \&quot;ft\&quot; 또는 \&quot;nft\&quot;인 것들 중 ca-filter 값과 같은 것들만 결과물로 반환.  (optional)
     * @param range (csv) 조회 범위 지정 (블록번호 또는 Unix time) (optional)
     * @param size 응답 아이템 개수(min&#x3D;1, max&#x3D;1000, default&#x3D;100) (optional)
     * @param cursor 특정 위치를 지정하기 위한 오프셋 (optional)
     * @return PageableTransfers
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PageableTransfers getTransfersByEoa(String xChainId, String address, String kind, String caFilter, String range, Long size, String cursor) throws ApiException {
        ApiResponse<PageableTransfers> resp = getTransfersByEoaWithHttpInfo(xChainId, address, kind, caFilter, range, size, cursor);
        return resp.getData();
    }

    /**
     * EOA로 토큰 송/수신 기록 조회
     * 특정 EOA의 토큰 거래내역을 조회합니다. 기능적으로 &#x60;GET /v2/transfer&#x60;와 같습니다. 단, 결과로 반환된 Transfer 객체에서 이 EOA가 토큰을 받았다면 &#x60;to&#x60;가, 토큰을 보냈다면 &#x60;from&#x60;이 EOA와 같습니다.     거래내역은 KLAY 전송 (&#x60;KlayTransfer&#x60;), FT 전송 (&#x60;FtTransfer&#x60;), NFT 전송 (&#x60;NftTransfer&#x60;)으로 나뉘며 FT, NFT 전송의 경우 토큰 전송을 실행한 &#x60;KlayTransfer&#x60; 전송내역이 조회 결과에 포함됩니다. FT, NFT 전송을 실행한 &#x60;KlayTransfer&#x60;의 &#x60;value&#x60;는 0일 수 있습니다. 이와 관련된 자세한 설명은 &#x60;GET /v2/transfer&#x60;를 참조하여 주시기 바랍니다.  ## 거래유형  * &#x60;kind&#x60; 파라메터를 설정하여 여러 유형의 거래내역을 조회할 수 있습니다 * &#x60;kind&#x60;가 설정되지 않으면 모든 유형의 거래내역이 조회됩니다  ## 조회기간  * &#x60;range&#x60;의 경우 &#x60;range&#x3D;{from},{to}&#x60; 포맷으로 질의합니다 * &#x60;{from}&#x60;과 &#x60;{to}&#x60;가 정수일 경우 Unix time으로, 16진수 문자열일 경우 블록 번호로 간주합니다 * &#x60;{to}&#x60;값이 없는 경우 현재 시간 또는 최신 블록 번호가 사용됩니다 * 최대 검색기간은 6개월로 제한됩니다 (Unix time, 블록 두 경우 모두 해당) 
     * @param xChainId  Klaytn 체인 네트워크 ID (1001 or 8217) (required)
     * @param address 검색기준이 되는 EOA, 검색결과의 from 또는 to가 제시된 address값과 일치 (required)
     * @param kind (csv) [“klay”, “ft”, “nft”] 중 포함 할 유형, 지정안될 경우 모든 유형을 조회  (optional)
     * @param caFilter 조회할 FT 또는 NFT 컨트랙트의 주소, 설정될 경우 &#x60;transferType&#x60;이 \&quot;ft\&quot; 또는 \&quot;nft\&quot;인 것들 중 ca-filter 값과 같은 것들만 결과물로 반환.  (optional)
     * @param range (csv) 조회 범위 지정 (블록번호 또는 Unix time) (optional)
     * @param size 응답 아이템 개수(min&#x3D;1, max&#x3D;1000, default&#x3D;100) (optional)
     * @param cursor 특정 위치를 지정하기 위한 오프셋 (optional)
     * @return ApiResponse&lt;PageableTransfers&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<PageableTransfers> getTransfersByEoaWithHttpInfo(String xChainId, String address, String kind, String caFilter, String range, Long size, String cursor) throws ApiException {
        com.squareup.okhttp.Call call = getTransfersByEoaValidateBeforeCall(xChainId, address, kind, caFilter, range, size, cursor, null, null);
        Type localVarReturnType = new TypeToken<PageableTransfers>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * EOA로 토큰 송/수신 기록 조회 (asynchronously)
     * 특정 EOA의 토큰 거래내역을 조회합니다. 기능적으로 &#x60;GET /v2/transfer&#x60;와 같습니다. 단, 결과로 반환된 Transfer 객체에서 이 EOA가 토큰을 받았다면 &#x60;to&#x60;가, 토큰을 보냈다면 &#x60;from&#x60;이 EOA와 같습니다.     거래내역은 KLAY 전송 (&#x60;KlayTransfer&#x60;), FT 전송 (&#x60;FtTransfer&#x60;), NFT 전송 (&#x60;NftTransfer&#x60;)으로 나뉘며 FT, NFT 전송의 경우 토큰 전송을 실행한 &#x60;KlayTransfer&#x60; 전송내역이 조회 결과에 포함됩니다. FT, NFT 전송을 실행한 &#x60;KlayTransfer&#x60;의 &#x60;value&#x60;는 0일 수 있습니다. 이와 관련된 자세한 설명은 &#x60;GET /v2/transfer&#x60;를 참조하여 주시기 바랍니다.  ## 거래유형  * &#x60;kind&#x60; 파라메터를 설정하여 여러 유형의 거래내역을 조회할 수 있습니다 * &#x60;kind&#x60;가 설정되지 않으면 모든 유형의 거래내역이 조회됩니다  ## 조회기간  * &#x60;range&#x60;의 경우 &#x60;range&#x3D;{from},{to}&#x60; 포맷으로 질의합니다 * &#x60;{from}&#x60;과 &#x60;{to}&#x60;가 정수일 경우 Unix time으로, 16진수 문자열일 경우 블록 번호로 간주합니다 * &#x60;{to}&#x60;값이 없는 경우 현재 시간 또는 최신 블록 번호가 사용됩니다 * 최대 검색기간은 6개월로 제한됩니다 (Unix time, 블록 두 경우 모두 해당) 
     * @param xChainId  Klaytn 체인 네트워크 ID (1001 or 8217) (required)
     * @param address 검색기준이 되는 EOA, 검색결과의 from 또는 to가 제시된 address값과 일치 (required)
     * @param kind (csv) [“klay”, “ft”, “nft”] 중 포함 할 유형, 지정안될 경우 모든 유형을 조회  (optional)
     * @param caFilter 조회할 FT 또는 NFT 컨트랙트의 주소, 설정될 경우 &#x60;transferType&#x60;이 \&quot;ft\&quot; 또는 \&quot;nft\&quot;인 것들 중 ca-filter 값과 같은 것들만 결과물로 반환.  (optional)
     * @param range (csv) 조회 범위 지정 (블록번호 또는 Unix time) (optional)
     * @param size 응답 아이템 개수(min&#x3D;1, max&#x3D;1000, default&#x3D;100) (optional)
     * @param cursor 특정 위치를 지정하기 위한 오프셋 (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getTransfersByEoaAsync(String xChainId, String address, String kind, String caFilter, String range, Long size, String cursor, final ApiCallback<PageableTransfers> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getTransfersByEoaValidateBeforeCall(xChainId, address, kind, caFilter, range, size, cursor, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<PageableTransfers>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getTransfersByTxHash
     * @param xChainId  Klaytn 네트워크 체인 ID (1001 or 8217) (required)
     * @param transactionHash 조회할 트랜잭션 해시 (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getTransfersByTxHashCall(String xChainId, String transactionHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/v2/transfer/tx/{transaction-hash}"
            .replaceAll("\\{" + "transaction-hash" + "\\}", apiClient.escapeString(transactionHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xChainId != null)
        localVarHeaderParams.put("x-chain-id", apiClient.parameterToString(xChainId));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "basic" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getTransfersByTxHashValidateBeforeCall(String xChainId, String transactionHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'xChainId' is set
        if (xChainId == null) {
            throw new ApiException("Missing the required parameter 'xChainId' when calling getTransfersByTxHash(Async)");
        }
        // verify the required parameter 'transactionHash' is set
        if (transactionHash == null) {
            throw new ApiException("Missing the required parameter 'transactionHash' when calling getTransfersByTxHash(Async)");
        }
        
        com.squareup.okhttp.Call call = getTransfersByTxHashCall(xChainId, transactionHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * 트랜잭션 해시로 토큰 송/수신 기록 조회
     * 트랜잭션 해시로 거래내역을 조회합니다. 거래내역은 KLAY 전송 (&#x60;KlayTransfer&#x60;), FT 전송 (&#x60;FtTransfer&#x60;), NFT 전송 (&#x60;NftTransfer&#x60;)으로 나뉘며 FT, NFT 전송의 경우 토큰 전송을 실행한 &#x60;KlayTransfer&#x60; 전송내역이 조회 결과에 포함됩니다. FT, NFT 전송을 실행한 &#x60;KlayTransfer&#x60;의 &#x60;value&#x60;는 0일 수 있습니다. 이와 관련된 자세한 설명은 &#x60;GET /v2/transfer&#x60;를 참조하여 주시기 바랍니다. 
     * @param xChainId  Klaytn 네트워크 체인 ID (1001 or 8217) (required)
     * @param transactionHash 조회할 트랜잭션 해시 (required)
     * @return Transfers
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Transfers getTransfersByTxHash(String xChainId, String transactionHash) throws ApiException {
        ApiResponse<Transfers> resp = getTransfersByTxHashWithHttpInfo(xChainId, transactionHash);
        return resp.getData();
    }

    /**
     * 트랜잭션 해시로 토큰 송/수신 기록 조회
     * 트랜잭션 해시로 거래내역을 조회합니다. 거래내역은 KLAY 전송 (&#x60;KlayTransfer&#x60;), FT 전송 (&#x60;FtTransfer&#x60;), NFT 전송 (&#x60;NftTransfer&#x60;)으로 나뉘며 FT, NFT 전송의 경우 토큰 전송을 실행한 &#x60;KlayTransfer&#x60; 전송내역이 조회 결과에 포함됩니다. FT, NFT 전송을 실행한 &#x60;KlayTransfer&#x60;의 &#x60;value&#x60;는 0일 수 있습니다. 이와 관련된 자세한 설명은 &#x60;GET /v2/transfer&#x60;를 참조하여 주시기 바랍니다. 
     * @param xChainId  Klaytn 네트워크 체인 ID (1001 or 8217) (required)
     * @param transactionHash 조회할 트랜잭션 해시 (required)
     * @return ApiResponse&lt;Transfers&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Transfers> getTransfersByTxHashWithHttpInfo(String xChainId, String transactionHash) throws ApiException {
        com.squareup.okhttp.Call call = getTransfersByTxHashValidateBeforeCall(xChainId, transactionHash, null, null);
        Type localVarReturnType = new TypeToken<Transfers>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * 트랜잭션 해시로 토큰 송/수신 기록 조회 (asynchronously)
     * 트랜잭션 해시로 거래내역을 조회합니다. 거래내역은 KLAY 전송 (&#x60;KlayTransfer&#x60;), FT 전송 (&#x60;FtTransfer&#x60;), NFT 전송 (&#x60;NftTransfer&#x60;)으로 나뉘며 FT, NFT 전송의 경우 토큰 전송을 실행한 &#x60;KlayTransfer&#x60; 전송내역이 조회 결과에 포함됩니다. FT, NFT 전송을 실행한 &#x60;KlayTransfer&#x60;의 &#x60;value&#x60;는 0일 수 있습니다. 이와 관련된 자세한 설명은 &#x60;GET /v2/transfer&#x60;를 참조하여 주시기 바랍니다. 
     * @param xChainId  Klaytn 네트워크 체인 ID (1001 or 8217) (required)
     * @param transactionHash 조회할 트랜잭션 해시 (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getTransfersByTxHashAsync(String xChainId, String transactionHash, final ApiCallback<Transfers> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getTransfersByTxHashValidateBeforeCall(xChainId, transactionHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Transfers>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
