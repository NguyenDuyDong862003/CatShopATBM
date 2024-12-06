/* eslint-disable import/no-extraneous-dependencies */
import { docReady } from './utils';
import toastInit from './theme/toast';
import tooltipInit from './theme/tooltip';
import featherIconsInit from './theme/featherIcons';
import basicEchartsInit from './theme/charts/echarts/basic-echarts';
import listInit from './theme/list';
import anchorJSInit from './theme/anchor';
import popoverInit from './theme/popover';
import fromValidationInit from './theme/form-validation';
import docComponentInit from './docs';
import swiperInit from './theme/swiper';
import productDetailsInit from './theme/product-details';
import phoenixOffcanvasInit from './theme/phoenix-offcanvas';
import ratingInit from './theme/rater';
import quantityInit from './theme/quantity';
import dropzoneInit from './theme/dropzone';
import choicesInit from './theme/choices';
import tinymceInit from './theme/tinymce';
import responsiveNavItemsInit from './theme/responsiveNavItems';
import zeroRoadmapChartInit from './theme/charts/echarts/zero-rodamap-chart';
import flatpickrInit from './theme/flatpickr';
import detectorInit from './theme/detector';
import iconCopiedInit from './theme/icons';
import isotopeInit from './theme/isotope';
import bigPictureInit from './theme/bigPicture';
import countupInit from './theme/countUp';
import initMap from './theme/googleMap';
import todoOffcanvasInit from './theme/todoOffCanvas';
import wizardInit from './theme/wizard';
import glightboxInit from './theme/glightbox';

window.initMap = initMap;
docReady(detectorInit);
docReady(toastInit);
docReady(tooltipInit);
docReady(featherIconsInit);
docReady(basicEchartsInit);
docReady(listInit);
docReady(anchorJSInit);
docReady(popoverInit);
docReady(fromValidationInit);
docReady(docComponentInit);
docReady(swiperInit);
docReady(productDetailsInit);
docReady(ratingInit);
docReady(quantityInit);
docReady(dropzoneInit);
docReady(choicesInit);
docReady(tinymceInit);
docReady(responsiveNavItemsInit);
docReady(zeroRoadmapChartInit);
docReady(flatpickrInit);
docReady(iconCopiedInit);
docReady(isotopeInit);
docReady(bigPictureInit);
docReady(countupInit);
docReady(phoenixOffcanvasInit);
docReady(todoOffcanvasInit);
docReady(wizardInit);
docReady(glightboxInit);