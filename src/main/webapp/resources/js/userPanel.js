var userPanel_toggle = 0;
var openable = 0;
var content;

$(function () {

    /*** preformatting userPanel content for guest ***/
    var contentGuest = "<a class=\"registerPrompt\" href=\"register.jsp\">Don't have an account?<span class=\"registerPromptLink\">&nbsp;<u>Register</u></span></a></span><br><span class=\"credentialsPrompt\">Please supply your credentials:</span>";
    contentGuest += "<form action=\"authenticateUserServlet\" method=\"post\">";
    contentGuest += "<label id=\"usernamePrompt\" class=\"prompt\"> Username: </label> <input autocomplete=\"off\" name=\"username\" id=\"loginUsername\" class=\"loginUsername\" type=\"text\"/>";
    contentGuest += "<label id=\"passwordPrompt\" class=\"prompt\"> Password: </label> <input autocomplete=\"off\" name=\"password\" id=\"loginPassword\" class=\"loginPassword\" type=\"password\"/>";
    contentGuest += "<span id=\"buttonSection\" align=\"center\"> <button id=\"loginButton\"> Login </button> </span> </form> <span id=\"loginResult\"> </span>";

    /*** preformatting userPanel content for user ***/
    var contentUser = "<span class=\"user_option\"> <img src='images/home.jpg'> <a href='./getUserProfileServlet;jsessionid=" + jsessionid + "?user_id=" + global_user_id + "' title='My Profile [alt-e]' accesskey='e'>My Profile</a> </span> ";
    contentUser += "<span class=\"user_option\"> <img src='images/logout.png'> <a href=logout.jsp title='Log out'> Log out </a> </span> ";

    $("#loginPassword").on("keyup", function (event) {
        event.stopPropagation();
        if (event.keyCode === 13)
            $("#loginButton").click();
    });

    $("#userPanel").on("mouseover", function () {
        $("#userPanel").stop();
        $("#userPanel").animate({
            height: '250px'
        });

        $("#userPanel div.content").css("display", "block");
        $("#userPanel").css("border-bottom", "solid 2px #fcfcfc");

        if (global_username === "") {
            content = contentGuest;
            $("#userPanel div.content").html(content);
        }
        else {
            content = contentUser;
            $("#userPanel div.content").html(content);
        }
    });

    $("#userPanel").on("mouseleave", function () {
        $("#userPanel").stop();
        $("#userPanel div.content").css("display", "none");
        $("#userPanel").css("border-bottom", "none");
        $("#userPanel").animate({
            height: '70px'
        });
    });
});

$(".content").on("mouseover", function (event) {
    event.stopPropagation();
    event.preventDefault();
});

$(".userImg").on("mouseover", function (event) {
    event.stopPropagation();
    event.preventDefault();
});

$(".userInfo").on("mouseover", function (event) {
    event.stopPropagation();
    event.preventDefault();
});

 