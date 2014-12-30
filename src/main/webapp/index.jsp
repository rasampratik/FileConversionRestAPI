<html>
<%
String contextPath = request.getContextPath();
request.setAttribute("contextPath",contextPath);
%>
<head>


</head>
<body>
<form action = "http://69.204.42.16:8085/restmagic-ws/rest/api/file/csvToxls" method = "POST" enctype = "multipart/form-data" accept-charset = "UTF-8">
<h1 role="presentation">REST</h1>
<h4>CSV To XLS</h4>
<label for = "file">Select a file to be uploaded</label>
<input type = "file" name = "file" size = "50"> <br>
<input type = "submit" value = "Upload" tabindex = 0>
</form>
<br><br>

<form action = "http://69.204.42.16:8085/restmagic-ws/rest/api/file/jsonToxml" method = "POST" enctype = "multipart/form-data" accept-charset = "UTF-8">
<fieldset>
  <legend>JSON To XML</legend>
<label for = "file" tabindex = "0">Select a file to be uploaded</label>
<input type = "file" name = "file" size = "50" tabindex = "1"> <br>
<input type = "submit" value = "Upload" tabindex = "2">
 
</fieldset>


</form>

<form action = "http://69.204.42.16:8085/restmagic-ws/rest/api/files/docTopdf" method = "POST" enctype = "multipart/form-data" accept-charset = "UTF-8">
<fieldset>
  <legend>DOC To PDF</legend>
<label for = "file" tabindex = "0">Select a file to be uploaded</label>
<input type = "file" name = "file"  tabindex = "1"> <br>
<input type = "submit" value = "Upload" tabindex = "2">
 
</fieldset>


</form>
<div id="content">
</div>

<!-- JS Imports -->
<script type = "text/javascript">
var contextPath = "<%=request.getContextPath()%>"
</script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://underscorejs.org/underscore-min.js"></script>
  <script src="http://backbonejs.org/backbone-min.js"></script>
<script src ="routes.js" type = "text/javascript"></script>
<script src ="viewsPanel.js" type = "text/javascript"></script>
<script src ="app.js" type = "text/javascript"></script>
 


</body>
</html>
