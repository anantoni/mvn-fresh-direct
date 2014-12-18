var content = "";
var firstnameErrorFound = false;
var lastnameErrorFound = false;
var interestsErrorFound = false;
var namePattern = /[<>*&$#!~`\.;:{}\[\]()+,]/;
var interestPattern = /[<>*$#!~`\.;:{}\[\]()]/;
var interest_counter = 0;
var oldInterestsString = "";


function allowProfileCreation() {
    if (!firstnameErrorFound && !lastnameErrorFound && !interestsErrorFound) {
        $("#profileButton").removeAttr("disabled");
        $("#profileButton").addClass("profileButtonEnabled");
    }
    else {
        $("#profileButton").attr("disabled", "enabled");
        $("#profileButton").removeClass("profileButtonEnabled");
    }
}

function checkFirstnameValidity(event) {

    event.stopPropagation();
    if ($("#firstname").val() !== "") {

        if ($("#firstname").val().length > 40) {
            $("#firstnameError").html("<img src='images/invalid_input.png'> Too long").show('slow');
            firstnameErrorFound = true;
        }
        else if (namePattern.test($("#firstname").val())) {
            $("#firstnameError").html("<img src='images/invalid_input.png'> Invalid").show('slow');
            firstnameErrorFound = true;
        }
        else {
            $("#firstnameError").html("<img src='images/valid_input.png'>").show('slow');
            firstnameErrorFound = false;
        }

    }
    allowProfileCreation();

}

function checkLastnameValidity(event) {

    event.stopPropagation();
    if ($("#lastname").val() !== "") {

        if ($("#lastname").val().length > 40) {
            $("#lastnameError").html("<img src='images/invalid_input.png'> Too long").show('slow');
            lastnameErrorFound = true;
        }
        else if (namePattern.test($("#firstname").val())) {
            $("#lastnameError").html("<img src='images/invalid_input.png'> Invalid").show('slow');
            lastnameErrorFound = true;
        }
        else {
            $("#lastnameError").html("<img src='images/valid_input.png'>").show('slow');
            lastnameErrorFound = false;
        }

    }
    allowProfileCreation();
}

function checkInterestsValidity(event) {

    event.stopPropagation();
    if ($("#interests").val().length > 500) {
        $("#interestsError").html("<img src='images/invalid_input.png'> Too long").show('slow');
        interestsErrorFound = true;
    }
    else if (interestPattern.test($("#interests").val())) {
        $("#interestsError").html("<img src='images/invalid_input.png'> Invalid").show('slow');
        interestsErrorFound = true;
    }
    else {
        $("#interestsError").html("<img src='images/valid_input.png'>").show('slow');
        interestsErrorFound = false;
    }
    allowProfileCreation();
}

$(document).ready(function () {

    $("#profileButton").removeAttr("disabled");
    $("#profileButton").addClass("profileButtonEnabled");

    $("#firstname").bind("keyup", checkFirstnameValidity);
    $("#lastname").bind("keyup", checkLastnameValidity);
    $("#interests").bind("keyup", checkInterestsValidity);

    $(".toggleComments").bind("click", function (event) {
        var id = $(this).attr('id');
        $("#commentArea" + id).toggle();
        $("#addCommentArea" + id).hide();

    });

    $("#profileInfoButton").css("background-color", "#bbbbbb");

    if ($("#wall_visibility").val() === "1") {
        $("#WallSection").css("display", "block");
        $("#wallButton").css("background-color", "#bbbbbb");
        $("#InfoSection").css("display", "none");
        $("#profileInfoButton").css("background-color", "white");
    }

    $(".toggleCommentBar").bind("click", function () {
        var id = $(this).attr('id');
        $("#commentArea" + id).show();
        $("#addCommentArea" + id).show();
    });

    $("#profileFriendsButton").on("click", function () {

        $("#WallSection").css("display", "none")
        $("#InfoSection").css("display", "none");
        $("#EditProfileSection").css("display", "none");
        $("#ReceivedRequestsSection").css("display", "none");
        $("#PendingFriendRequestsSection").css("display", "none");
        $("#DeleteProfileSection").css("display", "none");
        $("#FriendsSection").css("display", "block");

        $("#profileFriendsButton").css("background-color", "#bbbbbb");
        $("#profileInfoButton").css("background-color", "white");
        $("#editProfileButton").css("background-color", "white");
        $("#profileFriendRequestsButton").css("background-color", "white");
        $("#profilePendingFriendRequestsButton").css("background-color", "white");
        $("#deleteProfileButton").css("background-color", "white");
        $("#wallButton").css("background-color", "white");

    });

    $("#profileInfoButton").on("click", function () {

        $("#WallSection").css("display", "none");
        $("#InfoSection").css("display", "block");
        $("#FriendsSection").css("display", "none");
        $("#EditProfileSection").css("display", "none");
        $("#ReceivedRequestsSection").css("display", "none");
        $("#PendingFriendRequestsSection").css("display", "none");
        $("#DeleteProfileSection").css("display", "none");

        $("#profileInfoButton").css("background-color", "#bbbbbb");
        $("#editProfileButton").css("background-color", "white");
        $("#profileFriendRequestsButton").css("background-color", "white");
        $("#profileFriendsButton").css("background-color", "white");
        $("#profilePendingFriendRequestsButton").css("background-color", "white");
        $("#deleteProfileButton").css("background-color", "white");
        $("#wallButton").css("background-color", "white");

    });

    $("#editProfileButton").on("click", function () {

        $("#WallSection").css("display", "none");
        $("#EditProfileSection").css("display", "block");
        $("#InfoSection").css("display", "none");
        $("#FriendsSection").css("display", "none");
        $("#ReceivedRequestsSection").css("display", "none");
        $("#PendingFriendRequestsSection").css("display", "none");
        $("#DeleteProfileSection").css("display", "none");

        $("#editProfileButton").css("background-color", "#bbbbbb");
        $("#profileFriendRequestsButton").css("background-color", "white");
        $("#profileFriendsButton").css("background-color", "white");
        $("#profileInfoButton").css("background-color", "white");
        $("#profilePendingFriendRequestsButton").css("background-color", "white");
        $("#deleteProfileButton").css("background-color", "white");
        $("#wallButton").css("background-color", "white");

    });

    $("#profileFriendRequestsButton").on("click", function () {

        $("#WallSection").css("display", "none");
        $("#EditProfileSection").css("display", "none");
        $("#InfoSection").css("display", "none");
        $("#FriendsSection").css("display", "none");
        $("#PendingFriendRequestsSection").css("display", "none");
        $("#ReceivedRequestsSection").css("display", "block");
        $("#DeleteProfileSection").css("display", "none");

        $("#profileFriendRequestsButton").css("background-color", "#bbbbbb");
        $("#profileInfoButton").css("background-color", "white");
        $("#editProfileButton").css("background-color", "white");
        $("#profileFriendsButton").css("background-color", "white");
        $("#profilePendingFriendRequestsButton").css("background-color", "white");
        $("#deleteProfileButton").css("background-color", "white");
        $("#wallButton").css("background-color", "white");

    });

    $("#profilePendingFriendRequestsButton").on("click", function () {

        $("#WallSection").css("display", "none");
        $("#DeleteProfileSection").css("display", "none");
        $("#EditProfileSection").css("display", "none");
        $("#InfoSection").css("display", "none");
        $("#FriendsSection").css("display", "none");
        $("#ReceivedRequestsSection").css("display", "none");
        $("#PendingFriendRequestsSection").css("display", "block");

        $("#profilePendingFriendRequestsButton").css("background-color", "#bbbbbb");
        $("#profileInfoButton").css("background-color", "white");
        $("#editProfileButton").css("background-color", "white");
        $("#profileFriendsButton").css("background-color", "white");
        $("#profileFriendRequestsButton").css("background-color", "white");
        $("#deleteProfileButton").css("background-color", "white");
        $("#wallButton").css("background-color", "white");

    });

    $("#deleteProfileButton").on("click", function () {

        $("#WallSection").css("display", "none");
        $("#InfoSection").css("display", "none");
        $("#EditProfileSection").css("display", "none");
        $("#ReceivedRequestsSection").css("display", "none");
        $("#PendingFriendRequestsSection").css("display", "none");
        $("#DeleteProfileSection").css("display", "block");
        $("#FriendsSection").css("display", "none");

        $("#profileFriendsButton").css("background-color", "white");
        $("#profileInfoButton").css("background-color", "white");
        $("#editProfileButton").css("background-color", "white");
        $("#profileFriendRequestsButton").css("background-color", "white");
        $("#profilePendingFriendRequestsButton").css("background-color", "white");
        $("#deleteProfileButton").css("background-color", "#bbbbbb");
        $("#wallButton").css("background-color", "white");

    });

    $("#wallButton").on("click", function () {

        $("#WallSection").css("display", "block");
        $("#InfoSection").css("display", "none");
        $("#EditProfileSection").css("display", "none");
        $("#ReceivedRequestsSection").css("display", "none");
        $("#PendingFriendRequestsSection").css("display", "none");
        $("#DeleteProfileSection").css("display", "none");
        $("#FriendsSection").css("display", "none");

        $("#profileFriendsButton").css("background-color", "white");
        $("#profileInfoButton").css("background-color", "white");
        $("#editProfileButton").css("background-color", "white");
        $("#profileFriendRequestsButton").css("background-color", "white");
        $("#profilePendingFriendRequestsButton").css("background-color", "white");
        $("#deleteProfileButton").css("background-color", "white");
        $("#wallButton").css("background-color", "#bbbbbb");

    });

});