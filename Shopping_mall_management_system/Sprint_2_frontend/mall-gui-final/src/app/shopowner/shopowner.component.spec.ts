import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopownerComponent } from './shopowner.component';

describe('ShopownerComponent', () => {
  let component: ShopownerComponent;
  let fixture: ComponentFixture<ShopownerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShopownerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShopownerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
