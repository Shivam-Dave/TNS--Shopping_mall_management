import { ApplicationConfig, importProvidersFrom, LOCALE_ID } from '@angular/core'; // Import LOCALE_ID
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// --- Import locale settings ---
import { registerLocaleData } from '@angular/common';
import localeIn from '@angular/common/locales/en-IN';

// --- Register the locale data ---
registerLocaleData(localeIn);


export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withFetch()),
    importProvidersFrom(FormsModule),
    { provide: LOCALE_ID, useValue: 'en-IN' } // <-- Provide the Indian locale
  ]
};
