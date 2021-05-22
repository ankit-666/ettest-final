var jq = document.createElement("script");

jq.addEventListener("load", proceed); // pass my hoisted function
jq.src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js";
document.querySelector("head").appendChild(jq);

function proceed () {
   $( document ).ready(function() {
    $('loginbtn').click(  function () {
                console.log("click");
                var emailId = document.getElementById("Email");
                var password = document.getElementById("Password");

                $.ajax({
                    url: '/ettest/login',    //Your api url
                    type: 'PUT',   //type is any HTTP method
                    contentType: "application/json; charset=utf-8",
                    data: {
                        "emialId": $("Email").val(),
                        "password": $("Password").val()
                    },      //Data as js object
                    success: function () {
                    	myFunction();
                    }
                });

            });

            function myFunction() {
  console.log("called");
};


})}