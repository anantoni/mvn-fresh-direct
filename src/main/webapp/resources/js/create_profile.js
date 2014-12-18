var content = "";
var firstnameErrorFound = true;
var lastnameErrorFound = true;
var interestsErrorFound = true;
var namePattern = /[<>*&$#!~`\.;:{}\[\]()+,]/;
var interestPattern = /[<>*$#!~`\.;:{}\[\]()+]/;

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

    $("#profileButton").attr("disabled", "enabled");
    $("#profileButton").removeClass("profileButtonEnabled");
    $("#firstname").bind("keyup", checkFirstnameValidity);
    $("#lastname").bind("keyup", checkLastnameValidity);
    $("#interests").bind("keyup", checkInterestsValidity);

});