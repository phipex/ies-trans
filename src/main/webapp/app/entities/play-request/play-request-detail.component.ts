import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { PlayRequest } from './play-request.model';
import { PlayRequestService } from './play-request.service';

@Component({
    selector: 'jhi-play-request-detail',
    templateUrl: './play-request-detail.component.html'
})
export class PlayRequestDetailComponent implements OnInit, OnDestroy {

    playRequest: PlayRequest;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private playRequestService: PlayRequestService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPlayRequests();
    }

    load(id) {
        this.playRequestService.find(id).subscribe((playRequest) => {
            this.playRequest = playRequest;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPlayRequests() {
        this.eventSubscriber = this.eventManager.subscribe(
            'playRequestListModification',
            (response) => this.load(this.playRequest.id)
        );
    }
}
