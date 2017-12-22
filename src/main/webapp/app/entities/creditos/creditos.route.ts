import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CreditosComponent } from './creditos.component';
import { CreditosDetailComponent } from './creditos-detail.component';
import { CreditosPopupComponent } from './creditos-dialog.component';
import { CreditosDeletePopupComponent } from './creditos-delete-dialog.component';

export const creditosRoute: Routes = [
    {
        path: 'creditos',
        component: CreditosComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gamestransactionApp.creditos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'creditos/:id',
        component: CreditosDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gamestransactionApp.creditos.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const creditosPopupRoute: Routes = [
    {
        path: 'creditos-new',
        component: CreditosPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gamestransactionApp.creditos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'creditos/:id/edit',
        component: CreditosPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gamestransactionApp.creditos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'creditos/:id/delete',
        component: CreditosDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gamestransactionApp.creditos.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
