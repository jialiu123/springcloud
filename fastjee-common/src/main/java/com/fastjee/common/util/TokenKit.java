/*
 * Copyright 2018 吴汶泽(wenzewoo@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fastjee.common.util;

import com.fastjee.common.Constant;
import com.xiaoleilu.hutool.lang.Base64;
import com.xiaoleilu.hutool.lang.Console;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author by wuwz on 2018/2/23
 */
@Log4j
public class TokenKit {

    public static String getToken(HttpServletRequest request) {
        String authorization = request.getHeader(Constant.Auth.TOKEN_HEADER);
        if (StrUtil.isNotEmpty(authorization)) {
            return StrUtil.subAfter(authorization, Constant.Auth.TOKEN_VALUE_PREV, false);
        }

        throw new IllegalArgumentException("The token header:‘" + Constant.Auth.TOKEN_HEADER + "’ is null.");
    }

    public static Claims parseToken(HttpServletRequest request) {
        return parseToken(getToken(request));
    }

    public static Claims parseToken(String token) {
        String signingKey4Base64 = Base64.encode(Constant.Auth.JWT_SIGNING_KEY.getBytes());
        try {
            return Jwts.parser()
                    .setSigningKey(signingKey4Base64)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error(StrUtil.format("解析token失败：signingKey={},token={}", signingKey4Base64, token));
            throw e;
        }
    }

    public static String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims.get("user_name", String.class);
    }

    public static String getUsername(HttpServletRequest request) {
        return getUsername(getToken(request));
    }

    public static List<String> getAuthorities(String token) {
        return parseToken(token).get("authorities", List.class);
    }

    public static List<String> getAuthorities(HttpServletRequest request) {
        return getAuthorities(getToken(request));
    }

    public static void main(String[] args) {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTkzODg0NjYsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJqdGkiOiI5MDMwNTIyMC01YTBjLTQ1ZWMtYTYyYi1hMGY3ODQ1YmYyOTUiLCJjbGllbnRfaWQiOiJmYXN0amVlIiwic2NvcGUiOlsid2ViYXBwIl19.rOMqDGaiPA3tQR2nrY4BilmarVGOfQSNbn6IX48W3j8";

        getAuthorities(token).forEach(Console::log);

        System.out.println(getUsername(token));
    }


}
