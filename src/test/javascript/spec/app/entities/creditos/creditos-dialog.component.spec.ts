/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { GamestransactionTestModule } from '../../../test.module';
import { CreditosDialogComponent } from '../../../../../../main/webapp/app/entities/creditos/creditos-dialog.component';
import { CreditosService } from '../../../../../../main/webapp/app/entities/creditos/creditos.service';
import { Creditos } from '../../../../../../main/webapp/app/entities/creditos/creditos.model';

describe('Component Tests', () => {

    describe('Creditos Management Dialog Component', () => {
        let comp: CreditosDialogComponent;
        let fixture: ComponentFixture<CreditosDialogComponent>;
        let service: CreditosService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GamestransactionTestModule],
                declarations: [CreditosDialogComponent],
                providers: [
                    CreditosService
                ]
            })
            .overrideTemplate(CreditosDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CreditosDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CreditosService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Creditos(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.creditos = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'creditosListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Creditos();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.creditos = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'creditosListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
