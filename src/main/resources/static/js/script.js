function clicked(e, msg) {
    if (!confirm('Czy na pewno chcesz ' + msg + '?')) e.preventDefault();
}
