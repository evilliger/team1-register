<%@ page import="com.address.register.models.AddressEntry" %>
<%@ page import="com.google.appengine.api.datastore.*" %>
<html>
<body>
	<h1>Update Entry</h1>
	
	<%
				Key addressBookKey = KeyFactory.createKey("AddressBook", "MyAddressBook");
				
				AddressEntry ae = new AddressEntry();
			
				if(request.getAttribute("addressentry")!=null){
				
					ae = (AddressEntry)request.getAttribute("addressentry");
			
				}
		%>
	
	<form method="post" action="../update" >
		<input type="hidden" name="key" id="key" 
			value="<%=KeyFactory.keyToString(ae.getKey()) %>" /> 
		
		<table>
			<tr>
				<td>
					UserName :
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="name" id="name" 
						value="<%=ae.getName() %>" />
				</td>
			</tr>
			<tr>
				<td>
					Email :
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="email" id="email" 
						value="<%=ae.getEmail() %>" />
				</td>
			</tr>
		</table>
		<input type="submit" class="update" title="Update" value="Update" />
	</form>
	
</body>
</html>