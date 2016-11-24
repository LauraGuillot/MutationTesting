/*******************************************************************************
 Script index.js
 --------------------------------------------------------------------------------
 Main functions for the game mechanisms : execution of a test and displaying of 
 the results.
 --------------------------------------------------------------------------------
 Author : Laura
 Last modification : 02/08/2016
 *******************************************************************************/

/**
 * FUNCTION TRYTEST - This function sends a unit test written by the user to the 
 * "resultServlet" servlet. The servlet will compile and execute the text and then 
 * returns the output.
 * @returns {void}
 */
function tryTest() {
    document.getElementById("runLoading").style.display = "block";
    document.getElementById("run").style.display = "none";
    var s = editor.getValue();
    $.post('resultServlet', {
        test: s,
        loc: document.getElementById("loc").value,
        projectName: document.getElementById("projectName").value,
        name: document.getElementById("name").value
    }, function (responseText) {
        document.getElementById("runLoading").style.display = "none";
        document.getElementById("run").style.display = "block";
        displayResult(responseText);
    });
}

/**
 * FUNCTION  DISPLAYRESULT - This function takes in parameter a message 
 * corresponding to an execution output. Depending on this message, it displays 
 * feedbacks to the user.
 * @param {String} s - Message given by the execution of a unit test on a project
 *  and his mutation. There are 4 possibilities for this message : 1)"success" - 
 *  The compilation is successful and the test has killed the mutant. 2) "fail1" 
 *  - The compilation is successful but the test has not killed the mutant. 
 *  3) "fail2" - The compilation is successful but  the test has broken the original 
 *  source code. 4) Other - The compilation has failed and the message is a list of 
 *  compilation errors
 * @returns {void}
 */
function displayResult(s) {
    switch (s) {
        case "success" :
            document.getElementById("compilationResult").innerHTML = "BUILD SUCCESSFUL";
            document.getElementById("output").innerHTML = "Well done! You have killed the mutant!";
            $("#output").removeClass("error");
            $("#output").addClass("success");

            BootstrapDialog.show({
                type: BootstrapDialog.TYPE_SUCCESS,
                title: 'WELL DONE!',
                message: ' YOU HAVE KILLED THE MUTANT!',
                buttons: [{
                        label: 'Next mutant',
                        action: function (dialogItself) {
                            dialogItself.close();
                            if (document.getElementById("n").value < 10) {
                                document.getElementById("load").style.display = "block";
                            }
                            var url = "index.htm?n=" + document.getElementById("n").value + "&&name=" + document.getElementById("name").value + "&&an=K" + "&&score=" + document.getElementById("score").value;
                            window.location = url;
                        }
                    }]
            });
            break;
        case "fail1" :
            document.getElementById("compilationResult").innerHTML = "BUILD SUCCESSFUL";
            document.getElementById("output").innerHTML = "Retry! Your test has not killed the mutant!";
            $("#output").removeClass("success");
            $("#output").addClass("error");
            break;
        case "fail2" :
            document.getElementById("compilationResult").innerHTML = "BUILD SUCCESSFUL";
            document.getElementById("output").innerHTML = "Uh oh! Your test has killed the original code! Retry!";
            $("#output").removeClass("success");
            $("#output").addClass("error");
            break;
        default :
            document.getElementById("compilationResult").innerHTML = "ERROR";
            $("#output").removeClass("success");
            $("#output").addClass("error");
            compErrors(s);
            break;
    }
}

/**
 * FUNCTION COMPERRORS - This method has for objective to display a list of 
 * compilation errors.
 * @param {String} s - This string corresponds to a compilation output. It is the 
 * concatenation of the different lines of this output delimited by the symbol #.
 * @returns {void}
 */
function compErrors(s) {
    var r = s.split("#");
    var output = document.getElementById("output");
    output.innerHTML = "";
    for (var i = 0; i < r.length; i++) {
        var c = document.createElement('div');
        c.innerHTML = r[i];
        output.appendChild(c);
    }
}

/**
 * FUNCTION EQUI - When a user declares the mutant equivalent, this function is called.
 * It displays a pop-up which ask the user to confirm his choice.
 * @returns {void}
 */
function equi() {
    BootstrapDialog.confirm({
        title: '',
        message: 'Do you want to label this mutant as equivalent?',
        type: BootstrapDialog.TYPE_INFO,
        closable: true,
        btnCancelLabel: 'Cancel',
        btnOKLabel: 'YES',
        btnOKClass: 'btn-info',
        callback: function (result) {
            if (result) {
                BootstrapDialog.show({
                    type: BootstrapDialog.TYPE_SUCCESS,
                    title: '',
                    message: ' YOU HAVE DECLARED THIS MUTANT EQUIVALENT.',
                    buttons: [{
                            label: 'Next mutant',
                            action: function (dialogItself) {
                                dialogItself.close();
                                if (document.getElementById("n").value < 10) {
                                document.getElementById("load").style.display = "block";
                                 }
                                var url = "index.htm?n=" + document.getElementById("n").value + "&&name=" + document.getElementById("name").value + "&&an=E" + "&&score=" + document.getElementById("score").value;
                                window.location = url;
                            }
                        }]
                });
            }
        }
    });
}



