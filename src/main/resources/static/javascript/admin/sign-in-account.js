$(document).ready(function () {

    function resetForm() {
        $("#sign_in_account_form").validate().resetForm();
    }

    /** MODAL CLOSE -> RESET FORM **/
    $("#sign_in_account_modal").on("hide.bs.modal", function () {
        resetForm();
    });

    /** CLICK BUTTON RESET **/
    $("#sia_reset").click(function () {
        resetForm();
    });

    /** VALIDATE REGISTER FORM **/
    $("#sign_in_account_form").validate({
        /** RULES **/
        rules: {
            /** USERNAME REQUIRED **/
            j_username: {
                required: true,
                minlength: 8,
                maxlength: 32
            },
            /** EMAIL ADDRESS REQUIRED **/
            j_password: {
                required: true,
                minlength: 8,
                maxlength: 32
            }
        },
        /** MESSAGE **/
        messages: {
            /** MESSAGE VALIDATE USERNAME **/
            j_username: {
                required: "Username is required!",
                minlength: "Username must be more than 8 character!",
                maxlength: "Username must be less than 32 character!"
            },
            /** MESSAGE VALIDATE EMAIL ADDRESS **/
            j_password: {
                required: "Password is required!",
                minlength: "Password must be more than 8 character!",
                maxlength: "Password must be less than 32 character!"
            }
        },
        /**
         *  SUBMIT FORM
         */
        submitHandler: function (form) {
            /**
             *  AJAX CALL SignInAccount CONTROLLER
             *  REQUEST BODY AppUser
             */
            var appUser = new Object();
            appUser.appUsername = $("#j_username").val();
            appUser.encryptedPassword = $("#j_password").val();
            var ajax_post = $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/SignInAccount",
                data: JSON.stringify(appUser),
                dataType: "json"
            });
            /** AJAX CALL SUCCESS **/
            ajax_post.done(function (data) {
                switch (data) {
                    case "DISABLED": {
                        var el = document.createElement("div");
                        el.innerHTML = "<a class='text-success' id='resend_confirmation_token_link' data-toggle='modal' data-target='#resend_confirmation_token_modal'>Click me</a> to receive new email confirm registration!";
                        swal({
                            title: "Action completed!",
                            text: "Your account has not been activated, please check your email address confirm registration!",
                            icon: "error",
                            content: el,
                            button: {
                                text: "OK",
                                closeModal: true,
                                visible: true,
                                className: "btn btn-danger btn-lg"
                            }
                        });
                        break;
                    }
                    case "LOCKED": {
                        var el = document.createElement("div");
                        el.innerHTML = "<a class='text-success' id='send_email_unlock_link' data-toggle='modal' data-target='#send_email_unlock_modal'>Click me</a> to receive email unlock your account!";
                        swal({
                            title: "Action completed!",
                            text: "Your account is locked!",
                            icon: "error",
                            content: el,
                            button: {
                                text: "OK",
                                closeModal: true,
                                visible: true,
                                className: "btn btn-danger btn-lg"
                            }
                        });
                        break
                    }
                    case "INCORRECT": {
                        swal({
                            title: "Action completed!",
                            text: "Username or Password is incorrect, please try again!",
                            icon: "error",
                            button: {
                                text: "OK",
                                closeModal: true,
                                visible: true,
                                className: "btn btn-danger btn-lg"
                            }
                        });
                        break
                    }
                    case "SUCCESS":
                    default: {
                        $.ajax({
                            type     : "POST",
                            cache    : false,
                            url      : $("#sign_in_account_form").attr("action"),
                            data     : $("#sign_in_account_form").serializeArray(),
                            success  : function(data) {
                                window.location.replace("/");
                            }
                        });
                    }
                }
            });
        }

    });

    /**
     *  IF OPEN FORM RESEND EMAIL CONFIRMATION TOKEN
     */
    $(document).on("click", "#resend_confirmation_token_link", function() {
        /** CLOSE SWEET ALERT **/
        swal.close();
        /** CLOSE FORM SIGN-IN **/
        $("#sign_in_account_modal").modal("hide");
    });


    /**
     *  IF OPEN FORM SEND EMAIL UNLOCK ACCOUNT
     */
    $(document).on("click", "#send_email_unlock_link", function() {
        /** CLOSE SWEET ALERT **/
        swal.close();
        /** CLOSE FORM SIGN-IN **/
        $("#sign_in_account_modal").modal("hide");
    });

});