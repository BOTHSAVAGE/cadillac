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

package com.bothsavage.cadillac.common.security.annotation;

import com.bothsavage.cadillac.common.security.component.CadillacResourceServerAutoConfiguration;
import com.bothsavage.cadillac.common.security.component.CadillacResourceServerConfiguration;
import com.bothsavage.cadillac.common.security.feign.CadillacFeignClientConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.lang.annotation.*;

/**
 * @author lengleng
 * @date 2022-06-04
 * <p>
 * 资源服务注解
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ CadillacResourceServerAutoConfiguration.class, CadillacResourceServerConfiguration.class,
		CadillacFeignClientConfiguration.class })
public @interface EnableCadillacResourceServer {

}