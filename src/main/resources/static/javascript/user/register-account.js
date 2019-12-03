$(document).ready(function () {

    /** CHECK/UNCHECK I READ AND AGREE -> DISABLE/ENABLE BUTTON SUBMIT **/
    $("#ra_read_and_agree").click(function () {
        /** GET STATUS CHECKBOX READ-AND-AGREE **/
        var agree = $("#ra_read_and_agree").is(":checked");
        /** AGREE CHECKED -> ENABLE SUBMIT BUTTON **/
        if (agree) {
            $("#ra_submit").removeClass("disabled");
            $("#ra_submit").prop("disabled", false);
        }
        /** AGREE UNCHECKED -> DISABLE SUBMIT BUTTON **/
        else {
            $("#ra_submit").addClass("disabled");
            $("#ra_submit").prop("disabled", true);
        }
    });

    /** RESET FORM **/
    function resetForm() {
        /** RESET FORM VALUE **/
        $("#register_account_form").validate().resetForm();
        /** DISABLED BUTTON SUBMIT **/
        $("#ra_submit").addClass("disabled");
        $("#ra_submit").prop("disabled", true);
    }

    /** CLICK BUTTON RESET **/
    $("#ra_reset").click(function () {
        resetForm();
    });

    /** MODAL CLOSE -> RESET FORM **/
    $("#register_account_modal").on("hide.bs.modal", function () {
        resetForm();
    });

    /**
     *  VALIDATE USERNAME EXISTS METHOD
     *  CHECK USERNAME EXISTS
     *  CALL UsernameExists CONTROLLER
     *  REQUEST PARAM username
     */
    $.validator.addMethod("app_username_exists", function (appUsername, element) {
        var ajax_exists = $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/AppUsernameExists",
            data: {
                appUsername: appUsername
            },
            dataType: "json",
            async: false
        });
        var exists = false;
        ajax_exists.done(function (data) {
            exists = !data;
        });
        return this.optional(element) || exists;
    });

    /** VALIDATE USER NAME MUST METHOD **/
    $.validator.addMethod("app_username_must", function (appUsername, element) {
        var regex = /^[a-z0-9]+$/i;
        return this.optional(element) || (isNaN(appUsername) && regex.test(appUsername));
    });

    /**
     *  VALIDATE EMAIL USED METHOD
     *  CHECK EMAIL ADDRESS USED
     *  AJAX CALL CONTROLLER EmailAddressUsed
     *  REQUEST PARAM emailAddress
     */
    $.validator.addMethod("email_address_used", function (emailAddress, element) {
        var ajax_used = $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/EmailAddressUsed",
            data: {
                emailAddress: emailAddress
            },
            dataType: "json",
            async: false
        });
        var used = false;
        ajax_used.done(function (data) {
            used = !data;
        });
        return this.optional(element) || used;
    });

    /** VALIDATE PASSWORD METHOD **/
    $.validator.addMethod("password_must", function (password, element) {
        var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,32}$/i;
        return this.optional(element) || regex.test(password);
    });

    /** VALIDATE PHONE NUMBER **/
    $.validator.addMethod("vietnam_phone_number", function (phoneNumber, element) {
        var regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
        return this.optional(element) || regex.test(phoneNumber);
    });

    /** VALIDATE FIRST NAME METHOD **/
    $.validator.addMethod("person_name", function (name, element) {
        var regex = /^((?![0-9\~\!\@\#\$\%\^\&\*\(\)\_\+\=\-\[\]\{\}\;\:\"\\\/\<\>\?]).)+$/;
        return this.optional(element) || regex.test(name);
    });

    /** VALIDATE REGISTER FORM **/
    $("#register_account_form").validate({
        /** RULES **/
        rules: {
            /** USERNAME REQUIRED, BETWEEN 8 - 32 CHARACTER **/
            ra_app_username: {
                required: true,
                app_username_must : true,
                app_username_exists: true,
                minlength: 8,
                maxlength: 32
            },
            /** EMAIL ADDRESS REQUIRED **/
            ra_email_address: {
                required: true,
                email: true,
                email_address_used: true
            },
            /** PASSWORD REQUIRED, BETWEEN 8 - 32 CHARACTER **/
            ra_password: {
                required: true,
                password_must: true,
                minlength: 8,
                maxlength: 32
            },
            /** CONFIRM PASSWORD REQUIRED, MATCH PASSWORD **/
            ra_confirm_password: {
                required: true,
                equalTo: "#ra_password"
            },
            /** VALIDATE FIRST NAME AND LAST NAME **/
            ra_first_name: {
                required: true,
                person_ame: true
            },
            ra_last_ame: {
                required: true,
                person_ame: true
            },
            /** VALIDATE GENDER **/
            gender: {
                required: true
            },
            /** VALIDATE DATE OF BIRTH **/
            ra_date_of_birth: {
                required: true,
                date: true
            },
            /** VALIDATE PHONE NUMBER **/
            ra_phone_number: {
                required: true,
                vietnam_phone_number: true
            }
        },
        /** MESSAGE **/
        messages: {
            /** MESSAGE VALIDATE USERNAME **/
            ra_app_username: {
                required: "Username is required!",
                app_username_exists: "Username exists, please try again!",
                app_username_must: "Username accept only letter or letter and number!",
                minlength: "Username must be more than 8 character!",
                maxlength: "Username must be less than 32 character!"
            },
            /** MESSAGE VALIDATE EMAIL ADDRESS **/
            ra_email_address: {
                required: "Email is required!",
                email: "Please enter a valid email address!",
                email_address_used: "Email address used for other account, please try again!"
            },
            /** MESSAGE VALIDATE PASSWORD **/
            ra_password: {
                required: "Password is required!",
                password_must: "Passwords must contain including uppercase, lowercase letters and numbers",
                minlength: "Password must be more than 8 character!",
                maxlength: "Password must be less than 32 character!"
            },
            /** MESSAGE VALIDATE CONFIRM PASSWORD **/
            ra_confirm_password: {
                required: "Confirm password is required!",
                equalTo: "Password and Password confirm must be same!"
            },
            /** MESSAGE VALIDATE FIRST NAME **/
            ra_first_ame: {
                required: "First name is required!",
                person_name: "Please enter a valid person first name!"
            },
            /** MESSAGE VALIDATE FIRST NAME **/
            ra_last_name: {
                required: "Last name is required!",
                person_name: "Please enter a valid person last name!"
            },
            /** MESSAGE VALIDATE GENDER **/
            ra_gender: {
                required: "Gender is required!"
            },
            /** MESSAGE VALIDATE DATE OF BIRTH **/
            ra_date_of_birth: {
                required: "Date of birth is required!",
                date: "Please enter a valid date of birth!"
            },
            /** MESSAGE VALIDATE PHONE NUMBER **/
            ra_phone_number: {
                required: "Phone number is required!",
                vietnam_phone_number: "Please enter a valid Vietnamese phone number!"
            }
        },
        /**
         *  SUBMIT FORM
         *  AJAX CALL CreateAppUser CONTROLLER
         *  REQUEST BODY AppUser(AppUserDetail)
         */
        submitHandler: function (form) {
            /**
             *  GET FORM VALUE
             */
            var appUsername = $("#ra_app_username").val();
            var emailAddress = $("#ra_email_address").val();
            var password = $("#ra_password").val();
            var firstName = $("#ra_first_name").val();
            var lastName = $("#ra_last_name").val();
            var gender = $("input[name='ra_gender']:checked").val();
            var dateOfBirth = $("#ra_date_of_birth").val();
            var phoneNumber = $("#ra_phone_number").val();
            var address = $("#ra_address").val();
            /** APP USER **/
            var appUser = new Object();
            appUser.appUsername = appUsername;
            appUser.password = password;
            /** APP USER DETAIL **/
            var appUserProfile = new Object();
            appUserProfile.emailAddress = emailAddress;
            appUserProfile.firstName = firstName;
            appUserProfile.lastName = lastName;
            appUserProfile.gender = gender;
            appUserProfile.dateOfBirth = dateOfBirth;
            appUserProfile.phoneNumber = phoneNumber;
            appUserProfile.address = address;

            appUser.appUserProfile = appUserProfile;

            /** APP USER ROLE **/
            var appUserRoleSet = new Array();
            var appUserRole = new Object();
            var appRole = new Object();
            appRole.appRoleName = "ROLE_GUEST";
            appUserRole.appRole = appRole;
            appUserRoleSet.push(appUserRole);

            appUser.appUserRoleSet = appUserRoleSet;
            /**
             *  AJAX CALL CreateAppUser CONTROLLER
             *  REQUEST BODY AppUser(AppUserDetail)
             */
            var ajax_create = $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/CreateAppUser",
                data: JSON.stringify(appUser),
                dataType: "json"
            });
            /** AJAX CALL SUCCESS **/
            ajax_create.done(function (data) {
                data? alertSuccess() : alertError();
            });
        }

    });

    /** ALERT MESSAGE SUCCESS ACTION **/
    function alertSuccess() {
        swal({
            title: "Action Completed!",
            text: "Register account success, please check your email address confirm registration!",
            icon: "success",
            button: {
                text: "OK",
                closeModal: true,
                visible: true,
                className: "btn btn-success btn-lg"
            }
        })
        .then(function(isOk) {
            if(isOk) {
                /** CLOSE MODAL RESEND EMAIL CONFIRMATION TOKEN **/
                $("#register_account_modal").modal("hide");
                /** RESET FORM RESEND EMAIL CONFIRMATION TOKEN VALUE **/
                resetForm();
                /** OPEN MODAL SIGN-IN ACCOUNT **/
                $("#sign_in_account_modal").modal("show");

            }
        });
    }

    /** ALERT MESSAGE ERROR ACTION **/
    function alertError() {
        swal({
            title: "Action completed!",
            text: "Error occurred, please try again!",
            icon: "error",
            button: {
                text: "OK",
                closeModal: true,
                visible: true,
                className: "btn btn-danger btn-lg"
            }
        });
    }

});