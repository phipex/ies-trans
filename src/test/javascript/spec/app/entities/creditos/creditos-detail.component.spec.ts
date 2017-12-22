/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { GamestransactionTestModule } from '../../../test.module';
import { CreditosDetailComponent } from '../../../../../../main/webapp/app/entities/creditos/creditos-detail.component';
import { CreditosService } from '../../../../../../main/webapp/app/entities/creditos/creditos.service';
import { Creditos } from '../../../../../../main/webapp/app/entities/creditos/creditos.model';

describe('Component Tests', () => {

    describe('Creditos Management Detail Component', () => {
        let comp: CreditosDetailComponent;
        let fixture: ComponentFixture<CreditosDetailComponent>;
        let service: CreditosService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GamestransactionTestModule],
                declarations: [CreditosDetailComponent],
                providers: [
                    CreditosService
                ]
            })
            .overrideTemplate(CreditosDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CreditosDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CreditosService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new Creditos(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.creditos).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
