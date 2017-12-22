/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { GamestransactionTestModule } from '../../../test.module';
import { PlayRequestDetailComponent } from '../../../../../../main/webapp/app/entities/play-request/play-request-detail.component';
import { PlayRequestService } from '../../../../../../main/webapp/app/entities/play-request/play-request.service';
import { PlayRequest } from '../../../../../../main/webapp/app/entities/play-request/play-request.model';

describe('Component Tests', () => {

    describe('PlayRequest Management Detail Component', () => {
        let comp: PlayRequestDetailComponent;
        let fixture: ComponentFixture<PlayRequestDetailComponent>;
        let service: PlayRequestService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GamestransactionTestModule],
                declarations: [PlayRequestDetailComponent],
                providers: [
                    PlayRequestService
                ]
            })
            .overrideTemplate(PlayRequestDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PlayRequestDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlayRequestService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new PlayRequest(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.playRequest).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
