import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { PlayRequestComponent } from './play-request.component';
import { PlayRequestDetailComponent } from './play-request-detail.component';
import { PlayRequestPopupComponent } from './play-request-dialog.component';
import { PlayRequestDeletePopupComponent } from './play-request-delete-dialog.component';

@Injectable()
export class PlayRequestResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const playRequestRoute: Routes = [
    {
        path: 'play-request',
        component: PlayRequestComponent,
        resolve: {
            'pagingParams': PlayRequestResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gamestransactionApp.playRequest.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'play-request/:id',
        component: PlayRequestDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gamestransactionApp.playRequest.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const playRequestPopupRoute: Routes = [
    {
        path: 'play-request-new',
        component: PlayRequestPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gamestransactionApp.playRequest.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'play-request/:id/edit',
        component: PlayRequestPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gamestransactionApp.playRequest.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'play-request/:id/delete',
        component: PlayRequestDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gamestransactionApp.playRequest.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
