function mostrarLoading(){
	var elements = document.getElementsByClassName('loading');
	
	for(var i = 0 ; i < elements.length ; i++){
		elements[i].style.display = 'block';
	}
}

function ocultarLoading(){
	var elements = document.getElementsByClassName('loading');
	
	for(var i = 0 ; i < elements.length ; i++){
		elements[i].style.display = 'none';
	}
}