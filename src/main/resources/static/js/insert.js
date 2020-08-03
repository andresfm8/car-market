validateYear = (inputYear) => {
	let yearRange = /^(199[8-9]|20[0-4]\d|2025)$/;
	if(!yearRange.test(inputYear.value)){
		alert("Wrong year");
		return false;
	}
}