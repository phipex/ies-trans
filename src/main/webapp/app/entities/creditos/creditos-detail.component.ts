import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Creditos } from './creditos.model';
import { CreditosService } from './creditos.service';

@Component({
    selector: 'jhi-creditos-detail',
    templateUrl: './creditos-detail.component.html'
})
export class CreditosDetailComponent implements OnInit, OnDestroy {

    creditos: Creditos;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private creditosService: CreditosService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCreditos();
    }

    load(id) {
        this.creditosService.find(id).subscribe((creditos) => {
            this.creditos = creditos;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCreditos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'creditosListModification',
            (response) => this.load(this.creditos.id)
        );
    }
}
