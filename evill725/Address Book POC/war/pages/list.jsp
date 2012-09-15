<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ page import="com.address.register.models.AddressEntry" %>
<html>
<head>
<style type="text/css">
	.LinkRow {cursor:pointer;}
</style>
</head>
<body>
	<h1>POC Address Book</h1>

	Function : <a href="/addressentry/add">Add Entry</a>
	<hr />

	<h2>All Entries</h2>
	<table border="1">
		<thead>
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Created Date</td>
				<td>Action</td>
			</tr>
		</thead>
		
		<%
					if(request.getAttribute("addressentryList")!=null){
						
						List<AddressEntry> aes = (List<AddressEntry>)request.getAttribute("addressentryList");
						if(!aes.isEmpty()){
							 for(AddressEntry ae : aes){
				%>
					<tr class="LinkRow" onclick="document.location = '/addressentry/list/<%=KeyFactory.keyToString(ae.getKey())%>';">
					  <td><%=ae.getName() %></td>
					  <td><%=ae.getEmail() %></td>
					  <td><%=ae.getDate() %></td>
					  <td><a href="/addressentry/delete/<%=KeyFactory.keyToString(ae.getKey()) %>">Delete</a></td>
					</tr>
		<%	
			
					}
		    
				}
			
		   	}
		%>
         
        </tr>
     
	</table>

	<%	
	AddressEntry updateEntity = new AddressEntry();

	if(request.getAttribute("updateEntity")!=null){
	
		updateEntity = (AddressEntry)request.getAttribute("updateEntity");

	%>
	<h1>Update Entry</h1>
	
	<form method="post" action="../update" >
		<input type="hidden" name="key" id="key" 
			value="<%=KeyFactory.keyToString(updateEntity.getKey()) %>" /> 
		
		<table>
			<tr>
				<td>
					UserName :
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="name" id="name" 
						value="<%=updateEntity.getName() %>" />
				</td>
			</tr>
			<tr>
				<td>
					Email :
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="email" id="email" 
						value="<%=updateEntity.getEmail() %>" />
				</td>
			</tr>
		</table>
		<input type="submit" class="update" title="Update" value="Update" />
	</form>
	<%
	}
	%>
</body>
</html>