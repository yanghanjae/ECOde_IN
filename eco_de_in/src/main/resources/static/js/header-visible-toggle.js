function sidebar_not_visible() {
  document.querySelector('#header').classList.toggle('not-visible');
  document.querySelector('.header').classList.toggle('active-header');
  document.querySelectorAll('.text').forEach(t => t.classList.toggle('deActive'));
  document.querySelectorAll('.menu').forEach(m => m.classList.toggle('text-center'));
  document.querySelector('.user-info').classList.toggle('deActive');
  document.querySelector('img[alt="logout-btn"]').classList.toggle('deActive');
  document.querySelector('.footer').classList.toggle('not-visible');
}