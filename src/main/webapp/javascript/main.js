// js/main.js
import { initLeasePage } from './controllers/LeasePageController.js';

document.addEventListener('DOMContentLoaded', () => {
  // leaseID injected from JSP
  const leaseIDFromJsp = window.leaseIDFromJsp;
  initLeasePage(leaseIDFromJsp);
});