/**
 * 
 */
var portal = portal ||{};

portal.router = Backbone.Router.extend({
	routes:{
		'':'home'
	},
	home:function(){
		var homeView = new portal.HomeView();
	}
})