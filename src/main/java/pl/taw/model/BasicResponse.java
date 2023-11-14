/**
 * Created by tomasz_taw
 * Date: 29.10.2023
 * Time: 16:03
 * Project Name: secretProperties
 * Description:
 */
package pl.taw.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse {

    private String word;
    private String message;

}
