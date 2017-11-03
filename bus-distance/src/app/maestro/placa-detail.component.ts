import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';
import { NgbTabChangeEvent } from '@ng-bootstrap/ng-bootstrap';

import { PlacaService } from '../maestro/placa.service';

@Component( {
    selector: 'app-placa-detail',
    templateUrl: './placa-detail.component.html'
} )
export class PlacaDetailComponent implements OnInit {
    model: any;

    constructor(
        private route: ActivatedRoute
        , private router: Router
        , private location: Location
        , private modelService: PlacaService
    ) {
    }

    ngOnInit() {
        this.route.params.subscribe( params => {
            this.modelService.detail( { id: +params['id'] } )
                .subscribe( resp => {
                    this.model = resp.model;
                } );
        } );
    }

    remove() {
        console.log( "Remove" );

        this.modelService.remove( this.model )
            .subscribe( resp => {
                this.location.back();
            } );
    }
}
