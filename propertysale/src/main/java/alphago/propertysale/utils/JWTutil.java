package alphago.propertysale.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @description: Jwt utilisation class
 *
 **/
public class JWTutil {
    private static final String SING = "SADQWD2^$^";
    private static final Algorithm algorithm = Algorithm.HMAC256(SING);
    private static final Calendar calendar = Calendar.getInstance();
    static {
        calendar.add(Calendar.DATE , 7);
    }
    private static final Date expiryDate = calendar.getTime();

    /**
    * @Description: Generate Jwt Token based on the Key, Value map
    */
    public static String getJwtToken(Map<String , String> map){
        JWTCreator.Builder tokenBuilder = JWT.create();
        map.forEach(tokenBuilder::withClaim);
//        tokenBuilder.withExpiresAt(expiryDate);
        return tokenBuilder.sign(algorithm);
    }

    /**
    * @Description: Decode JwtToken
    */
    public static DecodedJWT decodedJWT(String token) throws IllegalArgumentException{
        DecodedJWT verify = JWT.require(algorithm).build().verify(token);
        return verify;
    }
}