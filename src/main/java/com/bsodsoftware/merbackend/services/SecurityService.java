package com.bsodsoftware.merbackend.services;

import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;

@Service
public class SecurityService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Long validateToken(String jwtToken) throws Exception {
		Long ret = -1L;
		Base64.Decoder decoder = Base64.getUrlDecoder();
		
		SignatureAlgorithm sa = SignatureAlgorithm.HS256;
		SecretKeySpec secretKeySpec = new SecretKeySpec(usuarioService.getSecret().getBytes(), sa.getJcaName());
		
		String[] chunks = jwtToken.split("\\.");
		String tokenWithoutSignature = chunks[0] + "." + chunks[1];
		String signature = chunks[2];
		
		DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);

		if (!validator.isValid(tokenWithoutSignature, signature)) {
		    throw new Exception("Falló la verificación de integridad del token JWT");
		}

		JSONObject payload = new JSONObject(new String(decoder.decode(chunks[1])));
		ret = payload.getLong("id");
		
		
		return ret;
	}
}
