var portal = portal ||{};

var myRequest = new XMLHttpRequest();
var responseObj;
myRequest.onreadystatechange = function(){
	if(myRequest.readyState===4){
		console.log(myRequest.responseText);
		responseObj = jQuery.parseJSON(myRequest.responseText);
	}
}
myRequest.open("GET",'http://localhost:8085/restmagic-ws/rest/api/accessibility',true);
myRequest.send(null);
portal.HomeView = Backbone.View.extend({
	initialize:function(){
		this.render();
	},
	el:$('#content'),
	render:function(){
		var that = this;
		var element = portal.tpl.get('portalHome');
		
		var $myObj = $($.parseHTML(element));

	var accessList = new portal.AccessElementList();

	$myObj.find('*').not('script, style, noscript').each(function(){
		var tag = $(this).prop("tagName").toLowerCase().toString();

		if(responseObj[tag]!=undefined){
			$(this).attr(responseObj[tag].key,responseObj[tag].value);
		}
		
	})

		var template = _.template($myObj.html());
		$(that.el).html(template);
	}
})

portal.AccessElement = Backbone.Model.extend({
defaults:{
	key:'',
	value:''
}
});

portal.AccessElementList = Backbone.Collection.extend({
	model:portal.AccessElement,
	url:'http://localhost:8085/restmagic-ws/rest/api/accessibility/',
	parse:function(data){
		return data;
	}
});