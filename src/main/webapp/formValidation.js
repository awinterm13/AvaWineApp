/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateForm() {
    var x = document.forms["login"]["fontColor"].value;
    var y = document.forms["login"]["username"].value;
    if (x == null || x == "") {
        alert("Color must be filled out");
        return false;
    }
       if (y == null || y == "") {
        alert("Username must be filled out");
        return false;
    }
}

function validateFormAddEdit() {
    var x = document.forms["addEdit"]["productName"].value;
    var y = document.forms["addEdit"]["price"].value;
    var z = document.forms["addEdit"]["imageUrl"].value;
    if (x == null || x == "") {
        alert("Product Name must be filled out");
        return false;
    }
       if (y == null || y == "") {
        alert("Price must be filled out");
        return false;
    }
      if (z == null || z == "") {
        alert("Image URL must be filled out");
        return false;
    }
}
    

/**
 * <form name="myForm" action="demo_form.asp" onsubmit="return validateForm()" method="post">
Name: <input type="text" name="fname">
<input type="submit" value="Submit">
</form>

HTML Form Validation
HTML form validation can be performed automatically by the browser:

If a form field (fname) is empty, the required attribute prevents this form from being submitted:

HTML Form Example
<form action="demo_form.asp" method="post">
  <input type="text" name="fname" required>
  <input type="submit" value="Submit">
</form>
Try it Yourself »
 */

