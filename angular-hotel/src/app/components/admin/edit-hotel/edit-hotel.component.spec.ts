import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditHotelComponent } from './edit-hotel.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';

describe('EditHotelComponent', () => {
  let component: EditHotelComponent;
  let fixture: ComponentFixture<EditHotelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule
      ],
      declarations: [ EditHotelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditHotelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
