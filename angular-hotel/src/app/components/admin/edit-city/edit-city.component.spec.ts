import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCityComponent } from './edit-city.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

describe('EditCityComponent', () => {
  let component: EditCityComponent;
  let fixture: ComponentFixture<EditCityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        FormsModule
      ],
      declarations: [ EditCityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditCityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
