function isValidDate(dateField) {
    var str = dateField.value;
    var dt = new Date(str);
    if ('string' !== typeof str || isNaN(dt) || str !== dt.toISOString().substr(0, 10)) {
        // Display an error message
        alert("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
        // Set the focus back to the date field
        dateField.focus();
        // Prevent the form from being submitted
        return false;
    }
    return true;
}

function isValidDollarAmount(amountField) {
    var str = amountField.value;
    // The regular expression checks for any number of digits, 
    // optionally followed by a decimal point and exactly two digits.
    const regex = /^\d+(\.\d{2})?$/;

    if (regex.test(str)) {
        return true;
    } else if (/^\d+$/.test(str)) {
        // If the input is an integer (no decimal point), append .00
        amountField.value = str + '.00';
        return true;
    } else {
        // The input is not a valid dollar amount
        // Display an error message
        alert("Invalid dollar amount. Please enter a number or a number with two decimal places.");
        // Set the focus back to the amount field
        amountField.focus();
        // Prevent the form from being submitted
        return false;
    }
}
