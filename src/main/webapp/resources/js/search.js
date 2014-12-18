$(document).ready(function () {

    $("#search_type").on("click", function (event) {

        if ($("#search_type").val() === "age") {
            $("#nameBar").css("display", "none");
            $("#interestBar").css("display", "none");
            $("#ageSearch").css("display", "inline-block");
        }
        else if ($("#search_type").val() === "interests") {
            $("#nameBar").css("display", "none");
            $("#ageSearch").css("display", "none");
            $("#interestBar").css("display", "inline-block");
        }
        else if ($("#search_type").val() === "name") {
            $("#nameBar").css("display", "inline-block");
            $("#ageSearch").css("display", "none");
            $("#interestBar").css("display", "none");
        }
        else if ($("#search_type").val() === "advanced_search") {
            $("#nameBar").css("display", "inline-block");
            $("#ageSearch").css("display", "inline-block");
            $("#interestBar").css("display", "inline-block");
        }

    });

    $("#searchBar").on("click", function () {
        alert("troll");
    });

});


