/*******************************************************************************
Script navCode.js
--------------------------------------------------------------------------------
Functions enabling user to navigate in the source code and display it.
--------------------------------------------------------------------------------
Author : Laura
Last modification : 02/08/2016
*******************************************************************************/


/**
 * Variable cur : name of the class currently displayed in the "source code" div
 * @type String
 */
var cur = "";

/**
 * FUNCTION LOAD - This function is called during the page loading.
 * It initializes the variable cur with the mutated class and displlay this class.
 * @returns {void}
 */
function load() {

    cur = document.getElementById("mutClass").value;
    document.getElementById(cur).style.display = "block";
    document.getElementById("className").innerHTML = cur;
}

/**
 * FUNCTION DISPLAYCODE - This function is called when the user wants to display a class.
 * It updates the variable "cur" with the class name and display the class.
 * @param {String} c - Name of a class
 * @returns {void}
 */
function displayCode(c) {
    document.getElementById("className").innerHTML = c;
    document.getElementById(cur).style.display = "none";
    document.getElementById(c).style.display = "block";
    cur = c;
}

/**
 * FUNCTION SHOWHIDE - When users clicks on a package, it displays or hides the package content
 * (that is to say the classes and the packages inside).
 * @param {Node} t - <a> Node which corresponds to a package 
 * @returns {void}
 */
function showHide(t) {
    var n = t.parentNode;
    var nodes = n.childNodes;
    for (var i =0;i<nodes.length;i++) {
        if(nodes[i].tagName=="UL"){
        nodes[i].style.display = (nodes[i].style.display == "block") ? "none" : "block";
    }
    }
}