//var usernamePattern = /[/<>*^%#!~`.;:{}[]()+,]/;
var usernamePattern = /[<>*&$#!~`\.;:{}\[\]()+,]/;
var passwordPattern = /[<>*&$#!~`\.;:{}\[\]()+,]/;
var emailPattern = /^\s*[\w\-\+_]+(\.[\w\-\+_]+)*\@[\w\-\+_]+\.[\w\-\+_]+(\.[\w\-\+_]+)*\s*$/;
var usernameError = true;
var passwordError = true;
var passwordConfirmError = true;
var emailError = true;


function parse_status(xml) {
    var status = null;
    $(xml).find("status").each(function () {
        status = $(this).text();

    });
    if (status === "success" || status === "SUCCESS") {
        return 0;
    }
    else
        return 1;
}

/********************************* CHECK REGISTER USERNAME ***********************************************/

function checkRegisterUsernameValidity(event) {

    event.stopPropagation();
    if ($("#registerUsername").val() !== "") {

        if ($("#registerUsername").val().length < 4) {
            $("#registerUsernameError").html("<img src='images/invalid_input.png'> Too short").show('slow');
            usernameError = true;
        }
        else if ($("#registerUsername").val().length > 40) {
            $("#registerUsernameError").html("<img src='images/invalid_input.png'> Too long").show('slow');
            usernameError = true;
        }
        else if (usernamePattern.test($("#registerUsername").val())) {
            $("#registerUsernameError").html("<img src='images/invalid_input.png'> Invalid").show('slow');
            usernameError = true;
        }
        else {
            $("#registerUsernameError").html("<img src='images/valid_input.png'>").show('slow');
            usernameError = false;
            allowRegistration();

        }

    }

}

function checkRegisterUsernameAvailability(event) {

    event.stopPropagation();
    if (usernameError === false) {

        $("#registerUsernameError").html("<img class='loading' src='images/loadingLogin9.gif'>");
        /*checking username availability*/
        $.post("ajax/checkUsernameAvailability.jsp", {username: $("#registerUsername").val()}, function (xml) {

            if (xml.length > 0) {

                var status = jQuery.trim($(xml).find("status").text());

                if (status === "SUCCESS") {
                    $("#registerUsernameError").html("<img src='images/valid_input.png'>").show('slow');
                    usernameError = false;
                    allowRegistration();

                }
                else {

                    var error = jQuery.trim($(xml).find("error").text());
                    usernameError = true;
                    if (error === "ALREADY_EXISTS")
                        $("#registerUsernameError").html("<img src='images/invalid_input.png'> Username taken").show('slow');

                    else if (error === "BAD_REQUEST")
                        $("#registerUsernameError").html("<img src='images/invalid_input.png'> Unknown Error").show('slow');

                    else
                        $("#registerUsernameError").html("<img src='images/invalid_input.png'> Internal Error").show('slow');

                }
            }
            else {
                $("#registerUsernameError").html("AJAX Error");
                usernameError = true;
            }
        });
    }

}

function checkRegisterEmailValidity(event) {
    event.stopPropagation();
    if ($("#registerEmail").val() !== "") {

        emailError = true;
        if ($("#registerEmail").val().length < 7)
            $("#registerEmailError").html("<img src='images/invalid_input.png'> Too short");

        else if ($("#registerEmail").val().length > 40)
            $("#registerEmailError").html("<img src='images/invalid_input.png'> Too long");

        else if (!emailPattern.test($("#registerEmail").val()))
            $("#registerEmailError").html("<img src='images/invalid_input.png'> Invalid");

        else {
            $("#registerEmailError").html("Valid");
            $("#registerEmailError").html("<img src='images/valid_input.png'>").show('slow');
            emailError = false;
            allowRegistration();
        }
    }
}

function checkRegisterPasswordValidity() {

    passwordError = true;
    if ($("#registerPassword").val().length < 5)
        $("#registerPasswordError").html("<img src='images/invalid_input.png'> Too short").show('slow');

    else if ($("#registerPassword").val().length > 40)
        $("#registerPasswordError").html("<img src='images/invalid_input.png'> Too long");

    else if (passwordPattern.test($("#registerPassword").val()))
        $("#registerPasswordError").html("<img src='images/invalid_input.png'> Invalid");

    else {
        $("#registerPasswordError").html("<img src='images/valid_input.png'>").show('slow');
        passwordError = false;
        allowRegistration();

    }
    //checkRegisterPasswordConfirmValidity();

}

function checkRegisterPasswordConfirmValidity() {

    if ($("#registerPassword").val() === $("#registerPasswordConfirm").val()) {
        $("#registerPasswordConfirmError").html("<img src='images/valid_input.png'>");
        passwordConfirmError = false;
        allowRegistration();
    }
    else {
        $("#registerPasswordConfirmError").html("<img src='images/invalid_input.png'> Passwords don't match");
        passwordConfirmError = true;
    }


}

function checkRegisterEmailAvailability(event) {

    event.stopPropagation();
    if (emailError === false) {

        $("#registerEmailError").html("<img class='loading' src='images/loadingLogin9.gif'>");
        /*checking email availability*/
        $.post("ajax/checkEmailAvailability.jsp", {email: $("#registerEmail").val()}, function (xml) {

            if (xml.length > 0) {

                var status = jQuery.trim($(xml).find("status").text());
                if (status === "SUCCESS") {
                    $("#registerEmailError").html("<img src='images/valid_input.png'>").show('slow');
                    emailError = false;
                }
                else {
                    var error = jQuery.trim($(xml).find("error").text());
                    emailError = true;

                    if (error === "ALREADY_EXISTS")
                        $("#registerEmailError").html("<img src='images/invalid_input.png'> Email taken").show('slow');

                    else if (error === "BAD_REQUEST")
                        $("#registerEmailError").html("<img src='images/invalid_input.png'> Unknown Error").show('slow');

                    else
                        $("#registerEmailError").html("<img src='images/invalid_input.png'> Internal Error").show('slow');

                }
            }
            else {
                $("#registeremailError").html("AJAX Error");
                emailError = true;
            }
        });
    }

}

function allowRegistration() {
    if (!usernameError && !passwordError && !passwordConfirmError && !emailError) {
        console.log("ready to register");
        $("#registerButton").removeAttr("disabled");
        $("#registerButton").addClass("registerButtonEnabled");
                    
    }
    else {
        if (usernameError)
            console.log("username error");
        else if (passwordError)
             console.log("password error");
         else if (passwordConfirmError)
             console.log("password confirm error");
         else if (emailError)
             console.log("email error");
        $("#registerButton").attr("disabled", "enabled");
        $("#registerButton").removeClass("registerButtonEnabled");
    }
}

/**************************************** DOCUMENT READY FUNCTIONS **************************************/
$(document).ready(function () {

    $("#registerButton").attr("disabled", "enabled");
    $("#registerButton").removeClass("registerButtonEnabled");
    $("#registerUsername").bind("keyup", checkRegisterUsernameValidity);
    $("#registerUsername").bind("blur", checkRegisterUsernameAvailability);
    $("#registerEmail").bind("keyup", checkRegisterEmailValidity);
    $("#registerEmail").bind("blur", checkRegisterEmailAvailability);
    $("#registerPassword").bind("keyup", checkRegisterPasswordValidity);
    $("#registerPasswordConfirm").bind("keyup", checkRegisterPasswordConfirmValidity);
    $("#registerButton").ajaxSuccess(function (e, xhr, settings) {
        allowRegistration();
    });

});