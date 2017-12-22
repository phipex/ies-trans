/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { GamestransactionTestModule } from '../../../test.module';
import { PlayRequestDialogComponent } from '../../../../../../main/webapp/app/entities/play-request/play-request-dialog.component';
import { PlayRequestService } from '../../../../../../main/webapp/app/entities/play-request/play-request.service';
import { PlayRequest } from '../../../../../../main/webapp/app/entities/play-request/play-request.model';

describe('Component Tests', () => {

    describe('PlayRequest Management Dialog Component', () => {
        let comp: PlayRequestDialogComponent;
        let fixture: ComponentFixture<PlayRequestDialogComponent>;
        let service: PlayRequestService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GamestransactionTestModule],
                declarations: [PlayRequestDialogComponent],
                providers: [
                    PlayRequestService
                ]
            })
            .overrideTemplate(PlayRequestDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PlayRequestDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlayRequestService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new PlayRequest(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.playRequest = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'playRequestListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new PlayRequest();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.playRequest = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'playRequestListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
