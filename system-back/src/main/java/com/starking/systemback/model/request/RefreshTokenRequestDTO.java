package com.starking.systemback.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pedroRhamon
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDTO {

	private String token;
}
