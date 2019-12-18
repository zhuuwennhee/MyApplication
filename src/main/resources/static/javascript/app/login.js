$(document).ready(function () {

    function resetForm() {
        $("#login_form").validate().resetForm();
    }

    /** CLICK BUTTON RESET **/
    $("#login_reset").click(function () {
        resetForm();
    });

    /** CLICK BUTTON SIGN-IN **/
    $("#login_submit").click(function () {
        loading();
    });

    /** LOADING **/
    function loading() {
        /** LOADING SPINNER **/
        $("#login_spinner").removeClass("fa fa-sign-in");
        $("#login_spinner").addClass("fa fa-spinner fa-spin");
        $("#login_submit").prop("disabled", true);
        $("#login_reset").prop("disabled", true);
    }

    /** UNLOADING **/
    function unloading() {
        /** LOADING SPINNER **/
        $("#login_spinner").removeClass("fa fa-spinner fa-spin");
        $("#login_spinner").addClass("fa fa-sign-in");
        $("#login_submit").prop("disabled", false);
        $("#login_reset").prop("disabled", false);
    }

    /** VALIDATE REGISTER FORM **/
    $("#login_form").validate({
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
        }

    });

    /**
     *  IF OPEN FORM RESEND EMAIL CONFIRMATION TOKEN
     */
    $(document).on("click", "#resend_confirmation_token_link", function() {
        /** CLOSE SWEET ALERT **/
        swal.close();
    });

    /**
     *  IF OPEN FORM SEND EMAIL UNLOCK ACCOUNT
     */
    $(document).on("click", "#send_email_unlock_link", function() {
        /** CLOSE SWEET ALERT **/
        swal.close();
    });

});