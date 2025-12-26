<%@ taglib prefix="s" uri="/struts-tags"%> 
<div class="container">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="card" >
                    <div class="card-header">
                        <div class="card-title">Sign In</div>
                        <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a></div>
                    </div>     

                    <div style="padding-top:30px" class="card-body" >
                    <b><s:actionerror/></b>
						<s:form action="login">
						  <div class="form-group">
						    <label for="email">Email address:</label>
						    <s:textfield id="email" type="email"  name="email" class="form-control"   theme="simple" /> <s:fielderror fieldName="email"  cssClass="font-weight-bold"/>
						  </div>
						  <div class="form-group">
						    <label for="password">Password:</label>
						    <s:textfield id="password" type="password"  name="password" class="form-control" theme="simple" /> <s:fielderror fieldName="password" cssClass="font-weight-bold"/>
						  </div>
						
						  <s:submit type="submit" method="login" class="btn btn-primary"></s:submit>
						  </s:form>  
						  <div class="form-group">
                               <div class="col-md-12 control">
                                   <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                        Don't have an account! 
                                        <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                            Sign Up Here
                                        </a>
                                  </div>
                               </div>
                          </div> 

					</div>
					
             </div>                     
       </div>
</div>        
						
