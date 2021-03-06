var app=angular.module("app",['ngRoute','ngCookies'])

app.config(function($routeProvider){
	$routeProvider
	.when('/registration',{controller:'UserCtrl',templateUrl:'views/registrationform.html'})
	.when('/login',{controller:'UserCtrl',templateUrl:'views/login.html'})
	.when('/home',{controller:'HomeCtrl',templateUrl:'views/home.html'})
	.when('/getalljobs',{controller:'UserCtrl',templateUrl:'views/jobs.html'})
	.when('/updateprofile',{controller:'UserCtrl',templateUrl:'views/updateprofile.html'})
	.when('/addjob',{controller:'JobCtrl',templateUrl:'views/jobform.html'})
	.when('/getalljobs',{controller:'JobCtrl',templateUrl:'views/listofjobs.html'})
	.when('/updatejob/:id',{controller:'JobCtrl',templateUrl:'views/updatejobform.html'})
	.when('/addblogpost',{controller:'BlogPostCtrl',templateUrl:'views/blogpostform.html'})

	.when('/getblog/:id',{controller:'BlogInDetailsCtrl',templateUrl:'views/blogindetails.html'})
	.when('/getblogs',{controller:'BlogPostCtrl',templateUrl:'views/listofblogsapproved.html'})
	.when('/getblogswaitingofapproval',{controller:'BlogPostCtrl',templateUrl:'views/listofblogwaitingforapproval.html'})
	.when('/getblogwaitingforapproval/:id',{controller:'BlogInDetailsCtrl',templateUrl:'views/blogapprovalform.html'})
	.when('/updateblogform/:id',{controller:'BlogInDetailsCtrl',templateUrl:'views/updateblogpostform.html'})
	.when('/getnotification/:id',{controller:'NotificationCtrl',templateUrl:'views/notificationondetails.html'})
	.when('/suggestedusers',{controller:'FriendCtrl',templateUrl:'views/suggestedusers.html'})
	.when('/pendingrequests',{controller:'FriendCtrl',templateUrl:'views/pendingrequest.html'})
	.when('/listoffriends',{controller:'FriendCtrl',templateUrl:'views/friendslist.html'})
	.when('/uploadprofilepic',{templateUrl:'views/uploadpicture.html'})
	.when('/chat',{templateUrl:'views/chat.html',controller:'ChatCtrl'})
	.otherwise({templateUrl:'views/home.html'})
})

app.run(function($rootScope, $location, $cookieStore,  UserService){
	if($rootScope.user==undefined)
		$rootScope.user=$cookieStore.get('userDetails')
		
	$rootScope.logout=function(){
		alert('Entering into logout')
		UserService.logout().then(function(response){
			
			console.log('successfully logged out')
			delete $rootScope.user
			$cookieStore.remove('userDetatils')
			$location.path('/login')
		},function(response){
			
			console.log('email is null')
			if($rootScope.user!=undefined)
			delete $rootScope.user
			$cookieStore.remove('userDetatils')
			$location.path('/login')
		})
	}
})