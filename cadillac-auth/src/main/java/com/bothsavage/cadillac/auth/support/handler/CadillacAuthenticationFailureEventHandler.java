/*
 * Copyright (c) 2020 cadillac4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bothsavage.cadillac.auth.support.handler;

import com.bothsavage.cadillac.admin.api.entity.SysLog;
import com.bothsavage.cadillac.common.core.util.R;
import com.bothsavage.cadillac.common.core.util.SpringContextHolder;
import com.bothsavage.cadillac.common.log.event.SysLogEvent;
import com.bothsavage.cadillac.common.log.util.LogTypeEnum;
import com.bothsavage.cadillac.common.log.util.SysLogUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lengleng
 * @date 2022-06-02
 */
@Slf4j
public class CadillacAuthenticationFailureEventHandler implements AuthenticationFailureHandler {

	private final MappingJackson2HttpMessageConverter errorHttpResponseConverter = new MappingJackson2HttpMessageConverter();

	/**
	 * Called when an authentication attempt fails.
	 * @param request the request during which the authentication attempt occurred.
	 * @param response the response.
	 * @param exception the exception which was thrown to reject the authentication
	 * request.
	 */
	@Override
	@SneakyThrows
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {
		String username = request.getParameter(OAuth2ParameterNames.USERNAME);

		log.info("?????????{} ????????????????????????{}", username, exception.getLocalizedMessage());
		SysLog logVo = SysLogUtils.getSysLog();
		logVo.setTitle("????????????");
		logVo.setType(LogTypeEnum.ERROR.getType());
		logVo.setException(exception.getLocalizedMessage());
		// ????????????????????????
		Long startTime = System.currentTimeMillis();
		Long endTime = System.currentTimeMillis();
		logVo.setTime(endTime - startTime);
		logVo.setCreateBy(username);
		logVo.setUpdateBy(username);
		SpringContextHolder.publishEvent(new SysLogEvent(logVo));
		// ??????????????????
		sendErrorResponse(response, exception);
	}

	private void sendErrorResponse(HttpServletResponse response, AuthenticationException exception) throws IOException {
		ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
		httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
		this.errorHttpResponseConverter.write(R.failed(exception.getLocalizedMessage()), MediaType.APPLICATION_JSON,
				httpResponse);
	}

}
