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

package com.bothsavage.cadillac.codegen;

import com.bothsavage.cadillac.common.datasource.annotation.EnableDynamicDataSource;
import com.bothsavage.cadillac.common.feign.annotation.EnableCadillacFeignClients;
import com.bothsavage.cadillac.common.security.annotation.EnableCadillacResourceServer;
import com.bothsavage.cadillac.common.swagger.annotation.EnableCadillacDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lengleng
 * @date 2020/03/11 代码生成模块
 */
@EnableCadillacDoc
@EnableDynamicDataSource
@EnableCadillacFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableCadillacResourceServer
public class CadillacCodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadillacCodeGenApplication.class, args);
	}

}
