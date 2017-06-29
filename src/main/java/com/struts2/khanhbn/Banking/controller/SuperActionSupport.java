/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.struts2.khanhbn.Banking.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Base Action class for the Tutorial package.
 */
public class SuperActionSupport extends ActionSupport implements ServletResponseAware, ServletRequestAware  {
	
	private static final long serialVersionUID = 3281812627912773253L;
	
	
	protected HttpServletResponse servletResponse;
	protected HttpServletRequest servletRequest;
	
	
	
	protected Map<String, Object> session;

	public Map<String, Object> getSession(){
		session = ActionContext.getContext().getSession();
		return session;
	}


	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.servletRequest = arg0;
	}

	
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.servletResponse = arg0;
	}



	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

    
	
	
	
	
}
