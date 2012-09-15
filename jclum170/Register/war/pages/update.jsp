<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ page import="com.olympics.register.models.AddressEntry" %>

<html>
<body>
	<h1>Update AddressEntry</h1>

		<%
			
				AddressEntry e = new AddressEntry();
			
				if(request.getAttribute("entity")!=null){
				
					e = (AddressEntry)request.getAttribute("entity");
			
				}
		%>
	<form method="post" action="../update" >
		<input type="hidden" name="key" id="key" 
			value="<%=KeyFactory.keyToString(e.getKey()) %>" /> 
		<table>
			<tr>
				<td>
					UserName :
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="name" id="name" 
						value="<%=e.getName() %>" />
				</td>
			</tr>
			<tr>
				<td>
					Email :
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="email" id="email" 
						value="<%=e.getEmail() %>" />
				</td>
			</tr>
		</table>
		<input type="submit" class="update" title="Update" value="Update" />
	</form>
</body>
</html>