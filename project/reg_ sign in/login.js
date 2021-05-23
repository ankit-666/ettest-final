var jq = document.createElement("script");

jq.addEventListener("load", proceed); // pass my hoisted function
jq.src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js";
document.querySelector("head").appendChild(jq);

function proceed () {
   $( document ).ready(function() {

            $('#signupbtn').click(  function () {
                        $.ajax({
                            url: 'http://192.168.0.6:9000/ettest/register',    //Your api url
                            type: 'POST',   //type is any HTTP method
                            contentType: "application/json; charset=utf-8",
                            data: JSON.stringify({
                              "firstName": $("#firstName").val(),
                              "lastName": $("#Lastname").val(),
                              "userType": $("#Usertype").val(),
                              "email": $("#EmailS").val(),
                              "password": $("#PasswordS").val()
                            }),      //Data as js object
                            success: function (result) {
                              console.log('my message' + result);
                            }
                        });

                    });
            function myFunction() {
  console.log("called");
};


})}
