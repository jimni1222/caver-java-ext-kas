/*
 * Token History API
 * # Introduction  Token History API allows users to search for information and transfer records on KLAY, FT (KIP-7, Labeled ERC-20), and NFT (KIP-17, Labeled ERC-721) tokens. You can use Token History API to check the records of a specific EOA transferring KLAY, retrieve NFT information, or other purposes.  For more details on Token History API, refer to our [tutorial](https://klaytn.com).  For any questions regarding this document or KAS, visit [the developer forum](https://forum.klaytn.com/).  
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.tokenhistory.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * MtTransferContract
 */


public class MtTransferContract {
  @SerializedName("address")
  private String address = null;

  public MtTransferContract address(String address) {
    this.address = address;
    return this;
  }

   /**
   * Contract address (20-byte)
   * @return address
  **/
  @Schema(example = "0x5e47b195eeb11d72f5e1d27aebb6d341f1a9bedb", required = true, description = "Contract address (20-byte)")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MtTransferContract mtTransferContract = (MtTransferContract) o;
    return Objects.equals(this.address, mtTransferContract.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MtTransferContract {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
