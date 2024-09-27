const currentPage = window.location.pathname.split('/', -1)[1]
const currentMenu = document.querySelector(`.menu-a[data-page="${currentPage}"]`);

console.log(currentPage)

if (currentMenu) {
  currentMenu.classList.add('active-menu');
}