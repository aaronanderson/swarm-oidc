/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mercer.cpsg.swarm.oidc.deployment;

import java.util.logging.Logger;

import javax.servlet.ServletContext;

import com.mercer.cpsg.swarm.oidc.OIDC;
import com.mercer.cpsg.swarm.oidc.OIDCFraction;

import io.undertow.servlet.ServletExtension;
import io.undertow.servlet.api.DeploymentInfo;

public class OIDCServletExtension implements ServletExtension {
	private static final Logger LOG = Logger.getLogger(OIDCServletExtension.class.getName());

	@Override
	public void handleDeployment(DeploymentInfo deploymentInfo, ServletContext servletContext) {
		OIDC<?> oidcConfig = OIDCFraction.installedConfig();
		LOG.info("Registering OIDC authentication mechanism");
		deploymentInfo.addAuthenticationMechanism("OIDC", new OIDCAuthenticationMechanism.Factory(oidcConfig, deploymentInfo.getIdentityManager()));

	}

}