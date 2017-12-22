import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Creditos } from './creditos.model';
import { CreditosService } from './creditos.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-creditos',
    templateUrl: './creditos.component.html'
})
export class CreditosComponent implements OnInit, OnDestroy {
creditos: Creditos[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private creditosService: CreditosService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.creditosService.query().subscribe(
            (res: ResponseWrapper) => {
                this.creditos = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCreditos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Creditos) {
        return item.id;
    }
    registerChangeInCreditos() {
        this.eventSubscriber = this.eventManager.subscribe('creditosListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
