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

package com.bothsavage.cadillac.common.feign.sentinel.handle;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.bothsavage.cadillac.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * sentinel统一降级限流策略
 * <p>
 * {@link com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.DefaultBlockExceptionHandler}
 *
 * @author lengleng
 * @date 2020-06-11
 */
@Slf4j
public class CadillacUrlBlockHandler implements BlockExceptionHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
		log.error("sentinel 降级 资源名称{}", e.getRule().getResource(), e);

		response.setContentType(ContentType.JSON.toString());
		response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
		response.getWriter().print(JSONUtil.toJsonStr(R.failed(e.getMessage())));
	}

}